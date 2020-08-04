package com.lugew.winsim.example.service;

import com.lugew.winsim.entity.Entity;
import com.lugew.winsim.mapper.MySqlMapper;
import com.lugew.winsim.service.AbstractMySqlService;

public class AbstractService<T extends Entity<?>, I extends MySqlMapper<T>> extends AbstractMySqlService<T,I> {
    @Override
    public Long getCurrentUserId() {
        return null;
    }
}
