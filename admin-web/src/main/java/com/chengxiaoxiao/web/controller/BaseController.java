package com.chengxiaoxiao.web.controller;

import com.chengxiaoxiao.model.common.webeditor.DateEditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * 提供一些公用的方法及默认编辑器的设置
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:48 上午
 * @Description:
 */
@SuppressWarnings("all")
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
    }

    /**
     * 获取分页请求
     *
     * @return
     */
    protected PageRequest getPageRequest() {
        int page = 1;
        int size = 10;
        Sort sort = null;
        try {
            String sortName = request.getParameter("sortName");
            String sortOrder = request.getParameter("sortOrder");
            if (StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)) {
                if ("desc".equalsIgnoreCase(sortOrder)) {
                    sort = new Sort(Direction.DESC, sortName);
                } else {
                    sort = new Sort(Direction.ASC, sortName);
                }
            }
            String pageNumber = request.getParameter("page");
            if (!StringUtils.isBlank(pageNumber)) {
                page = Integer.parseInt(pageNumber);
            }
            page--;
            String pageSize = request.getParameter("size");
            if (!StringUtils.isBlank(pageSize)) {
                size = Integer.parseInt(pageSize);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        PageRequest pageRequest = new PageRequest(page, size);
        if (sort != null) {
            pageRequest = new PageRequest(page, size, sort);
        }

        return pageRequest;
    }

    /**
     * 获取分页请求
     *
     * @param sort 排序条件
     * @return
     */
    protected PageRequest getPageRequest(Sort sort) {
        int page = 0;
        int size = 10;
        try {
            String sortName = request.getParameter("sortName");
            String sortOrder = request.getParameter("sortOrder");
            if (StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)) {
                if ("desc".equalsIgnoreCase(sortOrder)) {
                    sort.and(new Sort(Direction.DESC, sortName));
                } else {
                    sort.and(new Sort(Direction.ASC, sortName));
                }
            }
            page = Integer.parseInt(request.getParameter("page")) - 1;
            size = Integer.parseInt(request.getParameter("size"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageRequest pageRequest = new PageRequest(page, size);
        if (sort != null) {
            pageRequest = new PageRequest(page, size, sort);
        }
        return pageRequest;
    }

    /**
     * 获取分页请求
     *
     * @return
     */
    protected Pageable getPageAble() {
        int page = 1;
        int size = 10;
        Sort sort = null;
        try {
            String sortName = request.getParameter("sortName");
            String sortOrder = request.getParameter("sortOrder");
            if (StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)) {
                if ("desc".equalsIgnoreCase(sortOrder)) {
                    sort = new Sort(Direction.DESC, sortName);
                } else {
                    sort = new Sort(Direction.ASC, sortName);
                }
            }
            String pageNumber = request.getParameter("page");
            if (!StringUtils.isBlank(pageNumber)) {
                page = Integer.parseInt(pageNumber);
            }
            page--;
            String pageSize = request.getParameter("size");
            if (!StringUtils.isBlank(pageSize)) {
                size = Integer.parseInt(pageSize);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        PageRequest pageRequest = new PageRequest(page, size);
        if (sort != null) {
            pageRequest = new PageRequest(page, size, sort);
        }
        return pageRequest;
    }
}
