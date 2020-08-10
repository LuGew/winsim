package com.lugew.winsim.example.entity;

import lombok.Data;

import javax.validation.constraints.Null;

/**
 * @author LuGew
 * @since 2020/7/31
 */
@Data
public class Validated {
    private String name;
    private String password;

    @Null
    public String getName() {
        return name;
    }
}
