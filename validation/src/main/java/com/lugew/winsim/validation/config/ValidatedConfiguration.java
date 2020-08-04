package com.lugew.winsim.validation.config;

import com.lugew.winsim.validation.aspect.ValidatedAspect;
import org.springframework.context.annotation.Bean;

/**
 * 验证器配置文件
 * 注册验证器切面。
 *
 * @author LuGew
 * @since 2020/8/2
 */
public class ValidatedConfiguration {
    @Bean
    public ValidatedAspect validatedAspect() {
        return new ValidatedAspect();
    }
}
