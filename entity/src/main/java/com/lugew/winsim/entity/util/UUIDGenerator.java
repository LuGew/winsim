package com.lugew.winsim.entity.util;


import com.lugew.winsim.util.UUIDUtils;
import tk.mybatis.mapper.genid.GenId;

public class UUIDGenerator implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UUIDUtils.generate();
    }
}