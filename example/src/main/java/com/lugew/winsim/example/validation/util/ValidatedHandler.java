package com.lugew.winsim.example.validation.util;

import com.lugew.winsim.example.validation.annotation.Valid;
import com.lugew.winsim.example.validation.annotation.Validated;
import com.lugew.winsim.example.validation.validator.Null;
import com.lugew.winsim.example.validation.validator.Validator;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 字段验证器的处理器
 *
 * @author LuGew
 * @since 2020/7/30
 */
@UtilityClass
@Slf4j
public class ValidatedHandler {
    public void handle(Validated validated, Object object) {
        handle(validated.value(), object);
    }

    public void handle(Valid[] validArray, Object object) {
        Class<?> clazz = object.getClass();
        Map<Field, Set<Class<? extends Validator>>> fieldValidatorMap = mapDeclaredFields(validArray, clazz);
        mapNotDeclaredFields(fieldValidatorMap, clazz);
        validateFields(fieldValidatorMap, object);
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

    private void mapNotDeclaredFields(Map<Field, Set<Class<? extends Validator>>> fieldValidatorMap, Class<?> clazz) {
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (!fieldValidatorMap.containsKey(field)) {
                fieldValidatorMap.put(field, new HashSet<Class<? extends Validator>>(1) {{
                    add(Null.class);
                }});
            }
        }
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
                    e.printStackTrace();
                    throw new RuntimeException("cant get field" + fieldString + " in " + clazz.getName());
                }
            }
        }
        return fieldValidatorMap;
    }
}