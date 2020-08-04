package com.lugew.winsim.example.controller;

import com.lugew.winsim.example.entity.User;
import com.lugew.winsim.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;

/**
 * @author LuGew
 * @since 2020/7/29
 */
class UserControllerSpecification extends AbstractControllerSpecification {
    @InjectMocks
    private UserController control;
    @Mock
    private UserService service;

    @BeforeEach
    void setUp() {
        setUp(control);
//        setPrintable(true);
    }

    @Test
    void givenFileUploadCompletedWhenTrueThenOk() throws Exception {
        User meeting = new User();
        meeting.setId(1L);
        given(service.getOne(meeting)).willReturn(meeting);
        post("/user/test", meeting)
                .andExpect(jsonMatcher("$.code", 0))
                .andExpect(jsonMatcher("$.data.id", 1L));

    }

}