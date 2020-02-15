package com.chengxiaoxiao.model.common.webeditor;

import org.apache.commons.lang3.time.DateUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式数据转换器
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 1:18 下午
 * @Description: 时间格式字符串默认转换为日期对象
 */
public class DateEditor extends PropertyEditorSupport {

    private boolean emptyAsNull;
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String[] DATE_PATTERNS = {"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd",
            "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"};

    public DateEditor(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
    }

    public DateEditor(boolean emptyAsNull, String dateFormat) {
        this.emptyAsNull = emptyAsNull;
        this.dateFormat = dateFormat;
    }

    @Override
    public String getAsText() {
        Date date = (Date) getValue();
        return date != null ? new SimpleDateFormat(this.dateFormat).format(date) : "";
    }

    @Override
    public void setAsText(String text) {
        if (text == null) {
            setValue(null);
        } else {
            String str = text.trim();
            if ((this.emptyAsNull) && ("".equals(str))) {
                setValue(null);
            } else {
                try {
                    setValue(DateUtils.parseDate(str, DATE_PATTERNS));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        }
    }
}
