package com.lugew.winsim.example.aspect;

import com.lugew.winsim.example.controller.AbstractControllerSpecification;
import com.lugew.winsim.example.controller.UserController;
import com.lugew.winsim.example.entity.User;
import com.lugew.winsim.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * @author LuGew
 * @since 2020/7/30
 */
class FieldFilterAspectSpecification extends AbstractControllerSpecification {
    @InjectMocks
    UserController control;
    @Mock
    UserService service;

    @BeforeEach
    void setUp() {
        setUp(control);
//        setPrintable(true);
    }

    @Test
    void givenUsernameNotNullWenUsernameNotNullThenOk() throws Exception {
        User user = new User();
        user.setUsername("lugew");
        post("/user/test", user)
                .andExpect(jsonMatcher("$.code", 0));
    }

    @Test
    void givenUsernameNotNullWenUsernameIsNullThenError() throws Exception {
        User user = new User();
        user.setUsername("lugew");
        post("/user/test", user)
                .andExpect(jsonMatcher("$.code", 1));
    }
}