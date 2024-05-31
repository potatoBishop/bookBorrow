package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName(value = "donate")
@Data
public class Donate {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "donator")
    private String donator;

    @TableField(value = "donator_id")
    private Integer donator_id;

    @TableField(value = "book_name")
    private String bookName;

    @TableField(value = "author")
    private String author;

    @TableField(value = "book_no")
    private String bookNo;

    @TableField(value = "publisher")
    private String publisher;

    @TableField(value = "publish_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    @TableField(value = "book_lable")
    private String bookLable;

    @TableField(value = "durability")
    private Integer durability;

    @TableField(value = "donate_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date donateTime;

    @TableField(value = "donate_state")
    private Integer donateState;

    @TableField(value = "points")
    private Integer points;

    @TableField(value = "is_delete")
    private Integer isDelete;
}
