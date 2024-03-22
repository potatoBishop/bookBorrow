package com.etoak.java.controller;

import com.etoak.java.entity.Book;
import com.etoak.java.service.IBookService;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 书籍
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService bookService;


    /**查询所有书籍
     *
     */
    @RequestMapping("/selectAllBook")
    public ResultVO selectAllBook(){
        List<Book> result = bookService.selectAllBook();
        if (result != null){
            return ResultVO.success(result);
        } else {
            return ResultVO.failed("未查询到book表内容");
        }
    }

    /**获取一本书的借阅状态
     *
     * @param bookNo
     * @return
     */
    @RequestMapping("/checkBookStatus")
    public ResultVO checkBookStatus( String bookNo ){
        Integer status = bookService.checkBookStatus(bookNo);
        if ( status == null){
            return ResultVO.failed("未查询到相关数据");
        } else {
            return ResultVO.success(status);
        }
    }


    /**更新一本书的磨损状态
     *
     */
    @RequestMapping("/updateDurability")
    public ResultVO updateDurability(String bookNo, Integer durability){
        boolean flag = bookService.updateDurability(bookNo, durability);
        if( flag == true ){
            return ResultVO.success(null);
        } else {
            return ResultVO.failed();
        }
    }


    /**更新一本书的status
     *
     */
    @RequestMapping("updateBookStatus")
    public ResultVO updateBookStatus(String bookNo, Integer status){
        boolean flag = bookService.updateBookStatus(bookNo, status);
        if( flag == true ){
            return ResultVO.success(null);
        } else {
            return ResultVO.failed();
        }
    }
}
