package com.lugew.winsim.validation.validator;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * 抽象字段验证器
 * 扩展时需继承
 *
 * @author LuGew
 * @since 2020/7/30
 */
@Slf4j
public abstract class AbstractValidator implements Validator {
    public Object getFieldValue(Field field, Object object) {
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            log.error("class:{} access field:{} illegal,detail message:{}",
                    object.getClass().getName(),
                    field.getName(),
                    e.getMessage()
            );
        }
        return value;
    }

    @Override
    public boolean validate(Field field, Object object) {
        return true;
    }

}
