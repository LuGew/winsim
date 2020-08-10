package com.lugew.winsim.example.entity;

import com.lugew.winsim.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Null;

/**
 * @author LuGew
 * @since 2020/7/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends PageEntity {

    private String username;
    private String password;

    @Null
    public String getUsername() {
        return username;
    }
}
