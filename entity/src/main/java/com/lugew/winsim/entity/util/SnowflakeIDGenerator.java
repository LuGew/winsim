package com.lugew.winsim.entity.util;

import com.lugew.winsim.util.Snowflake;
import tk.mybatis.mapper.genid.GenId;

public class SnowflakeIDGenerator implements GenId<Long> {
    @Override
    public Long genId(String table, String column) {
        return Snowflake.nextId();
    }
}