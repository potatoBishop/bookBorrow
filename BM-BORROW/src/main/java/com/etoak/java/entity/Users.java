package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

// @ TableName value = 表名， 将Users实体类和users表联系起来
// 表名字 ： 全小写下划线          goods_order   数据库表名
// 实体类名字 ： 首字母大写驼峰命名  GoodsOrder    类名
//             首字母小写        goodsOrder    方法名,参数名，属性名，变量名

// lombok  @ Data 自动默认生成 get 和 set   仅在编译阶段使用即可

//@TableName(value = "users")
@Data
public class Users {

    /**
     * @ TableId 表示主键标识
     */
//    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
//    @TableField(value = "name")
    private String name;
//    @TableField(value = "stu_no")
    private String stuNo;
//    @TableField(value = "college")
    private String college;
//    @TableField(value = "major")
    private String major;
//    @TableField(value = "clazz")
    private String clazz;
//    @TableField(value = "gender")
    private Integer gender;
//    @TableField(value = "grade")
    private String grade;
//    @TableField(value = "id_no")
    private String idNo;
//    @TableField(value = "credit_level")
    private String creditLevel;
//    @TableField(value = "is_block")
    private Integer isBlock;
    /**
     *  Date.java.util.Date
     */

//    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
//    @TableField(value = "points")
    private Integer points;
}
