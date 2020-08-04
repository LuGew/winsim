package com.lugew.winsim.example.entity;

import com.lugew.winsim.entity.PageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LuGew
 * @since 2020/7/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends PageEntity {

    private String username;
    private String password;

}
