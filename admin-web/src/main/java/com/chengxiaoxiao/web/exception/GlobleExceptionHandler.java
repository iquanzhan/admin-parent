package com.chengxiaoxiao.web.exception;

import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();

        if (e instanceof BindException) {
            //JSR349 参数校验产生的异常
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();

            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else if (e instanceof MethodArgumentNotValidException) {
            //兼容低版本JSR303异常
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;

            return Result.error(CodeMsg.BIND_ERROR.fillArgs(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
        } else if (e instanceof GlobleException) {
            //处理系统逻辑产生的异常
            GlobleException ex = (GlobleException) e;
            return Result.error(ex.getCm());
        }else if(e instanceof AccessDeniedException){
            return Result.error(CodeMsg.AUTHENTICATION_ERROR);
        }
        else {
            //其他异常
            return Result.error(CodeMsg.OTHER_ERROR);
        }
    }
}
