package com.etoak.java.feign;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.etoak.java.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import com.etoak.java.entity.Book;

@FeignClient(value = "bm-book-service", path = "/book")
public interface IBookServiceFeign {

    @RequestMapping(value = "/checkBookStatus")
    ResultVO checkBookStatus(
            @RequestParam(value = "bookNo") String bookNo);

    @RequestMapping(value = "/updateBookStatus")
    ResultVO updateBookStatus(
            @RequestParam(value = "bookNo") String bookNo,
            @RequestParam(value = "status") Integer status);

    @RequestMapping(value = "/selectList")
    ResultVO selectList(
            @Param("ew") Wrapper<Book> queryWrapper);


    @RequestMapping(value = "/bookBorrow")
    public  ResultVO bookBorrow(@Param(value = "bookNo") String bookNo);

    @RequestMapping(value = "/bookReturn")
    public  ResultVO bookReturn(@Param(value = "bookNo") String bookNo);

    @PostMapping(value = "/addBook")
    public ResultVO addBook(@RequestBody Book book);

}
