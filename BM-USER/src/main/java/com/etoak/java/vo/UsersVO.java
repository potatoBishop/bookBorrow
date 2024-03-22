package com.etoak.java.vo;

import com.etoak.java.entity.Users;
import lombok.Data;

/**
 * 作为前后端交互
 * @ Data注解实现get and set
 */
@Data
public class UsersVO extends Users {
    //信用等级最大值
    private Integer creditLevelMax;
    //信用等级最小值
    private Integer creditLevelMin;



}
