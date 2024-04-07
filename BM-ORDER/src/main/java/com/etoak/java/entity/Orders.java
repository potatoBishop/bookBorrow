package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    @TableId(value = "id")
    private Integer id;

    @TableId(value = "order_no")
    private Integer orderNo;

    @TableId(value = "create_user")
    private String createUser;

    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    @TableField(value = "book_name")
    private String bookName;

    @TableField(value = "publisher")
    private String publisher;

    @TableField(value = "publish_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date publishTime;

    @TableField(value = "author")
    private String author;

    @TableField(value = "book_lable")
    private String bookLable;

    @TableField(value = "book_numbers")
    private Integer bookNumbers;

    @TableField(value = "total_price")
    private BigDecimal totalPrice;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "comment")
    private String comment;

    @TableField(value = "approval_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date approvalTime;
}
