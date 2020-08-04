package com.lugew.winsim.validation.aspect;

import com.lugew.winsim.util.ArrayUtil;
import com.lugew.winsim.validation.annotation.Valid;
import com.lugew.winsim.validation.annotation.Validated;
import com.lugew.winsim.validation.handler.ValidatedHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 字段验证切面
 *
 * @author LuGew
 * @since 2020/7/30
 */
@Aspect
@Slf4j
public class ValidatedAspect {

    /**
     * 任何方法参数上有{@link Validated}和{@link Valid}都被可能被环绕。
     */
    @Pointcut(

            "execution(* *(.., @com.lugew.winsim.validation.annotation.Validated (*), ..))"
                    + "||" +
                    "execution(* *(.., @com.lugew.winsim.validation.annotation.Valid (*), ..))"
    )
    public void pointcut() {
    }

    /**
     * 处理被{@link Validated}和{@link Valid}注解标注参数
     * 的方法。
     *
     * @param joinPoint 切点
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        if (ArrayUtil.isNotEmpty(arguments)) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Annotation[][] annotations = method.getParameterAnnotations();
            handleParameterAnnotations(arguments, annotations);
        }

    }

    /**
     * 处理参数
     *
     * @param arguments   参数数组
     * @param annotations 参数注解二维数组
     */
    private void handleParameterAnnotations(Object[] arguments, Annotation[][] annotations) {
        for (int i = 0; i < annotations.length; i++) {
            Object argument = arguments[i];
            Annotation[] argumentAnnotations = annotations[i];
            if (ArrayUtil.isNotEmpty(argumentAnnotations)) {
                for (Annotation annotation : argumentAnnotations) {
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    if (Validated.class.equals(annotationType)) {
                        ValidatedHandler.handle(argument, (Validated) annotation);
                    } else if (Valid.class.equals(annotationType)) {
                        ValidatedHandler.handle(argument, (Valid) annotation);
                    }
                }
            }
        }
    }


}
