package com.lugew.winsim.validation.annotation;


import com.lugew.winsim.validation.validator.NotNull;
import com.lugew.winsim.validation.validator.Validator;

import java.lang.annotation.*;

/**
 * 单个验证器
 *
 * @author LuGew
 * @since 2020/7/30
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Valid {
    Class<? extends Validator> validator() default NotNull.class;

    String[] fields();
}
