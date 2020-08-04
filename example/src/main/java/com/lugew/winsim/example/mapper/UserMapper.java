package com.lugew.winsim.example.mapper;

import com.lugew.winsim.example.entity.User;
import com.lugew.winsim.mapper.MySqlMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LuGew
 * @since 2020/7/29
 */
@Mapper
public interface UserMapper extends MySqlMapper<User> {

}
