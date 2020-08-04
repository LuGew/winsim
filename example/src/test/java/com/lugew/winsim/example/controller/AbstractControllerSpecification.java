package com.lugew.winsim.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.lugew.winsim.controller.AbstractController;
import com.lugew.winsim.entity.Entity;
import com.lugew.winsim.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;

/**
 * @author LuGew
 * @since 2020/7/16
 */

@Slf4j
@ExtendWith({MockitoExtension.class})
public abstract class AbstractControllerSpecification {

    protected MockMvc mockMvc;
    protected boolean printable;

    protected <T extends Entity<?>, S extends Service<T>, C extends AbstractController<T, S>> void setUp(C controller) {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    protected boolean isPrintable() {
        return this.printable;
    }

    protected void setPrintable(boolean printable) {
        this.printable = printable;
    }

    protected ResultActions post(String uriTemplate, Object
            object) throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(uriTemplate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSONObject.toJSONString(object))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
        print(resultActions);
        return resultActions;
    }

    protected ResultActions get(String uriTemplate, MultiValueMap<String, String> params) throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get(uriTemplate)
                        .params(params)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
        print(resultActions);
        return resultActions;
    }

    protected void print(ResultActions resultActions) throws Exception {
        if (isPrintable()) {
            resultActions.andDo(MockMvcResultHandlers.print());
        }
    }

    protected ResultMatcher jsonMatcher(String expression, Object expectedValue) {
        return MockMvcResultMatchers.jsonPath(expression).value(expectedValue);
    }
}
