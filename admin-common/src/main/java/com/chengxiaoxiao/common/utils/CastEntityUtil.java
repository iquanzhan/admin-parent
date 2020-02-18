package com.chengxiaoxiao.common.utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * 转换实体类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/18 1:46 下午
 * @Description:
 */
public class CastEntityUtil {
    /**
     * 把List<Object[]>转换为对应的实体类的集合
     *
     * @param list  Object[]集合
     * @param clazz 对应实体类的class对象
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];

        //确定构造方法
        for (int i = 0; i < co.length; i++) {
            c2[i] = co[i].getClass();
        }

        for (Object[] o : list) {
            Constructor<T> constructor = clazz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }
        return returnList;
    }
}
