package com.lugew.winsim.example.controller;

import com.lugew.winsim.example.entity.HibernateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author LuGew
 * @since 2020/8/7
 */
@ExtendWith({MockitoExtension.class})
class HibernateValidatorControllerSpecification extends AbstractControllerSpecification {
    @InjectMocks
    HibernateValidatorController controller;

    @BeforeEach
    void setUp() {
        setUp(controller);
    }

    @Test
    void givenNameIsNullWhenNameIsNullThenOK() throws Exception {
        HibernateValidator entity = new HibernateValidator();
        post("/hibernateValidator/nameIsNull", entity)
                .andExpect(jsonMatcher("$.code", 0));
    }

    @Test
    void givenNameIsNullWhenNameIsNotNullThenOK() throws Exception {
        HibernateValidator entity = new HibernateValidator();

        entity.setName("lugew");
        post("/hibernateValidator/nameIsNull", entity, MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenPasswordIsNotNullWhenNameIsNotNullThen400() throws Exception {
        HibernateValidator entity = new HibernateValidator();
        entity.setName("lugew");
        entity.setPassword("123456");
        post("/hibernateValidator/passwordIsNotNull", entity, MockMvcResultMatchers.status().isBadRequest());
    }
}