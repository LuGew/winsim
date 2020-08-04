package com.lugew.winsim.validation.validator;


import com.lugew.winsim.util.CollectionUtil;
import com.lugew.winsim.util.ObjectUtil;
import com.lugew.winsim.util.StringUtil;

import java.lang.reflect.Field;

/**
 * String/Collections包含元素不为空的验证器
 *
 * @author LuGew
 * @since 2020/7/31
 */
public class NotEmpty extends AbstractValidator {
    @Override
    public boolean validate(Field field, Object object) {
        Object value = getFieldValue(field, object);
        if (ObjectUtil.isNull(value)) {
            throw new RuntimeException(field.getName() + " must not empty,but is null");
        }
        if (StringUtil.isEmpty(value) && CollectionUtil.isEmpty(value)) {
            throw new RuntimeException(field.getName() + " must not empty,but is empty");
        }
        return true;
    }
}
