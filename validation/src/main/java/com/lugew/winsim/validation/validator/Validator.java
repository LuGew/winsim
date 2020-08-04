package com.lugew.winsim.validation.validator;

import java.lang.reflect.Field;

/**
 * 字段验证器
 *
 * @author LuGew
 * @since 2020/7/30
 */
public interface Validator {

    /**
     * 是否验证通过
     *
     * @param field  字段
     * @param object 对象
     * @return true通过，false不通过
     */
    boolean validate(Field field, Object object);
}
