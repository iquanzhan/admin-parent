package com.chengxiaoxiao.model.vaildator;


import com.chengxiaoxiao.model.annotation.validator.IsMobile;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        /** 判断值是否为必须的*/
        if (required) {
            return ValidatorUtil.isMobile(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return false;
            }
            return ValidatorUtil.isMobile(s);
        }
    }
}
