package com.chengxiaoxiao.model.vaildator;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static final Pattern p = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher matcher = p.matcher(mobile);
        return matcher.matches();
    }
}
