package com.chengxiaoxiao.web.exception;

import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;
}
