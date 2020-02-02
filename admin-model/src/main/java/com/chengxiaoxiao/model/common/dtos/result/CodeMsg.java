package com.chengxiaoxiao.model.common.dtos.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 错误码实体
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020-01-08 22:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeMsg implements Serializable {

    //通用异常
    public static CodeMsg ERROR = new CodeMsg(0, "success");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

    public static CodeMsg AUTHENTICATION_ERROR = new CodeMsg(403001, "抱歉，您没有权限访问本页面");

    //用户异常
    public static CodeMsg LOGIN_SUCCESS = new CodeMsg(0, "登录成功");
    public static CodeMsg LOGOUT_SUCCESS = new CodeMsg(0, "登出成功");

    public static CodeMsg USER_NOT_EXIST = new CodeMsg(404001, "用户名不存在");
    public static CodeMsg USER_LOCKED = new CodeMsg(200, "用户被冻结");
    public static CodeMsg USER_PASSWORD_INCORRENT = new CodeMsg(200, "用户名密码不正确");


    private Integer code;
    private String msg;

    /**
     * 添加带参数的错误异常
     *
     * @param obj
     * @return
     */
    public CodeMsg fillArgs(Object... obj) {
        int code = this.code;
        String message = String.format(msg, obj);
        return new CodeMsg(code, message);
    }

}
