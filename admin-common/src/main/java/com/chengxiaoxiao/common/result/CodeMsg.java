package com.chengxiaoxiao.common.result;

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

    public static CodeMsg ERROR = new CodeMsg(0, "success");

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
