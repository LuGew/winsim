package com.lugew.winsim.validation.validator;

import com.lugew.winsim.util.ObjectUtil;

import java.lang.reflect.Field;

/**
 * @author LuGew
 * @since 2020/7/30
 */
public class NotNull extends AbstractValidator {

    @Override
    public boolean validate(Field field, Object object) {
        Object value = getFieldValue(field, object);
        if (ObjectUtil.isNull(value)) {
            throw new RuntimeException(field.getName() + " cant be null");
        }
        return true;
    }
}
