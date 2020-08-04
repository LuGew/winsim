package com.lugew.winsim.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.lugew.winsim.entity.validator.Add;
import com.lugew.winsim.entity.validator.Delete;
import com.lugew.winsim.entity.validator.Update;
import com.lugew.winsim.entity.util.SnowflakeIDGenerator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author LuGew
 * @since 2020/5/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SnowflakeIDEntity extends AbstractEntity<Long> {
    @Id
    @KeySql(genId = SnowflakeIDGenerator.class)
    @ApiModelProperty(value = "id", example = "1256918346344042505")
    @Null(groups = {Add.class})
    @NotNull(groups = {Update.class, Delete.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
