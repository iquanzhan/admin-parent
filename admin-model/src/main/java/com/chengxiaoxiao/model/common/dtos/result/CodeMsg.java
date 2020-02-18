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
    //成功的code
    private static Integer CODE_SUCCESS = 0;

    //通用异常
    public static CodeMsg ERROR = new CodeMsg(CODE_SUCCESS, "success");
    public static CodeMsg OTHER_ERROR = new CodeMsg(500, "系统发生异常，请稍后重试");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

    public static CodeMsg AUTHENTICATION_TOKEN_EXPIRED = new CodeMsg(403000, "抱歉，TOKEN已过期");
    public static CodeMsg AUTHENTICATION_ERROR = new CodeMsg(403001, "抱歉，您没有权限访问本页面");

    public static CodeMsg ENTITY_NOT_EXIST = new CodeMsg(404, "实体不存在");
    public static CodeMsg ENTITY_CONVERT_ERROR = new CodeMsg(404, "数据传输实体：%s，转换异常！");

    //用户异常
    public static CodeMsg LOGIN_SUCCESS = new CodeMsg(CODE_SUCCESS, "登录成功");
    public static CodeMsg LOGOUT_SUCCESS = new CodeMsg(CODE_SUCCESS, "登出成功");

    public static CodeMsg USER_NOT_EXIST = new CodeMsg(100001, "用户信息不存在");
    public static CodeMsg USER_LOCKED = new CodeMsg(100002, "用户被冻结");
    public static CodeMsg USER_PASSWORD_INCORRENT = new CodeMsg(100003, "用户名密码不正确");

    //角色异常
    public static CodeMsg ROLE_NOT_EXIST = new CodeMsg(200001, "角色信息不存在");
    public static CodeMsg ROLE_ID_NOT_EXIST = new CodeMsg(200002, "请输入角色ID");


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
