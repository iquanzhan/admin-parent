package com.chengxiaoxiao.web.exception;

import com.chengxiaoxiao.model.common.dtos.CodeMsg;
import lombok.Getter;

public class GlobleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private CodeMsg cm;

    public GlobleException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.cm = codeMsg;
    }
}
