package com.lugew.winsim.example.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author LuGew
 * @since 2020/7/31
 */
@Data
public class HibernateValidator {
    private String name;
    @NotNull(groups = Password.class)
    private String password;

    @Null(groups = Nullable.class)
    @Length(min = 5, groups = Password.class)
    public String getName() {
        return name;
    }

    public interface Password {

    }

    public interface Nullable {

    }
}
