package com.chengxiaoxiao.common.utils;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:53 下午
 * @Description:
 */
public class ResultUtil {
    public static void responseJson(HttpServletResponse response, Object resultData) {

        String json = JSONObject.toJSONString(resultData);

        response.setContentType("text/json;charset=utf-8");
        try {
            response.getWriter().write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
