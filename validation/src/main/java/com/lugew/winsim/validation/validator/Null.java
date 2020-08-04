package com.lugew.winsim.validation.validator;


import com.lugew.winsim.util.ObjectUtil;

import java.lang.reflect.Field;

/**
 * 空验证器
 *
 * @author LuGew
 * @since 2020/7/30
 */
public class Null extends AbstractValidator {
    @Override
    public boolean validate(Field field, Object object) {
        Object value = getFieldValue(field, object);
        if (ObjectUtil.isNotNull(value)) {
            throw new RuntimeException(field.getName() + " must be null");
        }
        return true;
    }
}
