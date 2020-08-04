package com.lugew.winsim.example.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMap;

/**
 * @author LuGew
 * @since 2020/7/16
 */

@Slf4j
public abstract class AbstractMvcControllerSpecification {


    protected boolean printable;

    public abstract MockMvc mockMvc();

    protected boolean isPrintable() {
        return this.printable;
    }

    protected void setPrintable(boolean printable) {
        this.printable = printable;
    }

    protected ResultActions post(String uriTemplate, Object
            object) throws Exception {
        ResultActions resultActions = mockMvc().perform(
                MockMvcRequestBuilders.post(uriTemplate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JSONObject.toJSONString(object))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
        print(resultActions);
        return resultActions;
    }

    protected ResultActions get(String uriTemplate, MultiValueMap<String, String> params) throws Exception {
        ResultActions resultActions = mockMvc().perform(
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
