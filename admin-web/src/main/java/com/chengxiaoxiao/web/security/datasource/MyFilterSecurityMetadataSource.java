package com.chengxiaoxiao.web.security.datasource;

import com.chengxiaoxiao.model.repository.SysResourceRepository;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.web.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/21 2:42 下午
 * @Description:
 */
@Component
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;


    /*
     * 这个例子放在构造方法里初始化url权限数据，我们只要保证在 getAttributes()之前初始好数据就可以了
     */
    public MyFilterSecurityMetadataSource(SysResourceService sysResourceService) {
        List<SysResource> resourceList = sysResourceService.findAll();

        Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<>();
        AntPathRequestMatcher matcher;
        SecurityConfig config;
        ArrayList<ConfigAttribute> configs;
        for (SysResource sysResource : resourceList) {
            matcher = new AntPathRequestMatcher(sysResource.getSourceUrl(), sysResource.getHttpMethod());
            config = new SecurityConfig("ROLE_" + sysResource.getScourceKey());
            configs = new ArrayList<>();
            configs.add(config);
            map.put(matcher, configs);
        }

        this.requestMap = map;
    }


    /**
     * 在我们初始化的权限数据中找到对应当前url的权限数据
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        HttpServletRequest request = fi.getRequest();
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();

        // Lookup your database (or other source) using this information and populate the
        // list of attributes (这里初始话你的权限数据)
        //List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

        //遍历我们初始化的权限数据，找到对应的url对应的权限
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

