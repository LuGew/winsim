package com.lugew.winsim.example.service;

import com.lugew.winsim.example.entity.User;
import com.lugew.winsim.example.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * @author LuGew
 * @since 2020/7/29
 */
class UserServiceSpecification extends AbstractServiceSpecification {

    @Mock
    private UserMapper mapper;

    @InjectMocks
    @Spy
    private UserService service;


    @Test
    void test() {
        doReturn(1L).when(service).getCurrentUserId();
        User entity = new User();
        entity.setId(1L);
        when(mapper.updateByPrimaryKeySelective(entity)).thenReturn(1);
        assertThat(service.updateNotNull(entity)).isEqualTo(1);
    }

}