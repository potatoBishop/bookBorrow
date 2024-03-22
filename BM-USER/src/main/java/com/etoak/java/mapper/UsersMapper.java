package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <Users> 泛型， 理论性偏多
 */

/**
 * 继承
 * class and class extends
 * class and interface implements
 *
 * @ mapper注解将其交给springboot框架管理
 */

@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    int blockUser(@Param(value = "userId") Integer id);  //根据id禁用用户
    int updateUserCreditLevel(
            @Param(value = "userId") Integer id,
            @Param(value = "levelNumber") Integer levelNumber );
}
