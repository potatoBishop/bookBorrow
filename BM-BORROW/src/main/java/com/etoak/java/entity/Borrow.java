package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/***
 * @Author 高俊 QQ:1120934832
 * @Slogan 易途科技，精英启航
 */
@TableName(value = "borrow")
@Data
public class Borrow {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "stu_no")
    private String stuNo;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "book_no")
    private String bookNo;

    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "duration")
    private Integer duration;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "comment")
    private String comment;

    @TableField(value = "return_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnTime;
}
