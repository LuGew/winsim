package com.lugew.winsim.example.service;

import com.lugew.winsim.entity.Entity;
import com.lugew.winsim.mapper.MySqlMapper;
import com.lugew.winsim.service.AbstractService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 抽象业务逻辑层测试说明
 *
 * @author LuGew
 * @since 2020/7/28
 */
@ExtendWith(MockitoExtension.class)
class AbstractServiceSpecification {

    <T extends Entity<?>, M extends MySqlMapper<T>, S extends AbstractService<T, M>>
    void setUp(S service, M mapper) {
//        service.setMapper(mapper);

    }

}
