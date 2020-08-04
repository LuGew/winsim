package com.lugew.winsim.validation.handler;

import com.lugew.winsim.validation.annotation.Valid;
import com.lugew.winsim.validation.annotation.Validated;
import com.lugew.winsim.validation.validator.Null;
import com.lugew.winsim.validation.validator.Validator;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 字段验证器的处理器
 * 未进行限制的字段必须为空
 *
 * @author LuGew
 * @since 2020/7/30
 */
@UtilityClass
@Slf4j
public class ValidatedHandler {
    public void handle(Object object, Validated validated) {
        handle(object, validated.value());
    }

    public void handle(Object object, Valid... validArray) {
        Class<?> clazz = object.getClass();
        Map<Field, Set<Class<? extends Validator>>> fieldValidatorMap = mapDeclaredFields(validArray, clazz);
        mapNotDeclaredFields(fieldValidatorMap, clazz);
        validateFields(fieldValidatorMap, object);
    }

    private Map<Field, Set<Class<? extends Validator>>> mapDeclaredFields(Valid[] validArray, Class<?> clazz) {
        Map<Field, Set<Class<? extends Validator>>> fieldValidatorMap = new HashMap<>();
        for (Valid valid : validArray) {
            String[] fields = valid.fields();
            Class<? extends Validator> validator = valid.validator();

            for (String fieldString : fields) {
                try {
                    Field field = clazz.getDeclaredField(fieldString);
                    if (!fieldValidatorMap.containsKey(field)) {
                        fieldValidatorMap.put(field, new HashSet<>());
                    }
                    fieldValidatorMap.get(field).add(validator);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException("field " + fieldString + " not exist in " + clazz.getName());
                }
            }
        }
        return fieldValidatorMap;
    }

    private void mapNotDeclaredFields(Map<Field, Set<Class<? extends Validator>>> fieldValidatorMap, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!fieldValidatorMap.containsKey(field)) {
                fieldValidatorMap.put(field, new HashSet<Class<? extends Validator>>(1) {{
                    add(Null.class);
                }});
            }
        }
    }

    private void validateFields(Map<Field, Set<Class<? extends Validator>>> fieldValidatorMap, Object object) {
        for (Map.Entry<Field, Set<Class<? extends Validator>>> entry : fieldValidatorMap.entrySet()) {
            Field field = entry.getKey();
            Set<Class<? extends Validator>> validators = entry.getValue();
            for (Class<? extends Validator> validator : validators) {
                try {
                    validator.newInstance().validate(field, object);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("cant create new field validator:");
                }
            }
        }
    }


}
