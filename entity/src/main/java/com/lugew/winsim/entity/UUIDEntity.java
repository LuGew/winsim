package com.lugew.winsim.entity;

import com.lugew.winsim.entity.validator.Add;
import com.lugew.winsim.entity.validator.Delete;
import com.lugew.winsim.entity.validator.Update;
import com.lugew.winsim.entity.util.UUIDGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 基础UUID实体
 *
 * @author LuGew
 */
@ApiModel
public class UUIDEntity extends ModifiableEntity<String> {
    @Id
    @KeySql(genId = UUIDGenerator.class)
    @ApiModelProperty(value = "uuid", example = "uuid")
    @Null(groups = {Add.class})
    @NotNull(groups = {Update.class, Delete.class})
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
