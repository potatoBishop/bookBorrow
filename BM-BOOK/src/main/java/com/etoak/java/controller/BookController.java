package com.etoak.java.controller;

import com.etoak.java.constants.BookConstants;
import com.etoak.java.entity.Book;
import com.etoak.java.entity.Orders;
import com.etoak.java.feign.IOrdersServiceFeign;
import com.etoak.java.mapper.BookMapper;
import com.etoak.java.service.IBookService;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 书籍
 */
@RestController
@RequestMapping("/book")
@RefreshScope
public class BookController {

    @Autowired
    IBookService bookService;

    @Autowired
    IOrdersServiceFeign ordersServiceFeign;


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
    @RequestMapping("/updateBookStatus")
    public ResultVO updateBookStatus(String bookNo, Integer status){
        boolean flag = bookService.updateBookStatus(bookNo, status);
        if( flag == true ){
            return ResultVO.success(null);
        } else {
            return ResultVO.failed();
        }
    }

    // 教学============================================================================

    /**
     * 多条件查询数据列表
     * @param book
     * @return
     */
    @RequestMapping("/list")
    public ResultVO getBookList(Book book){
        List<Book> resultList =
                bookService.getList(book);
        return ResultVO.success(resultList);
    }

    /**
     * 新增书籍的方法
     * @param book
     * @return
     */
    @RequestMapping("/addBook")
    public ResultVO addBook(Book book){
        System.out.println("调用book/add服务");
        System.out.println(book);  // 在这里出了问题

//        int result = bookService.addBook(book);
        int result = bookService.addBook(book);
        System.out.println(result);
        if (result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed("新增书籍失败");
        }

    }


    /**更新书籍的方法
     *
     * @param book
     * @return
     */
    @RequestMapping("/update")
    public ResultVO updateBook(Book book){

        int result = bookService.updateBook(book);
        if (result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed("更新书籍失败");
        }
    }

    /**根据id删除书籍的方法
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteBookById")
    public ResultVO deleteBookById(Integer id){
        int result = bookService.deleteById(id);
        if (result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed("按照id删除书籍失败");
        }
    }

    /**根据bookNo删除书籍的方法
     *
     * @param bookNo
     * @return
     */
    @RequestMapping("/deleteBookByNo")
    public ResultVO deleteBookByNo(String bookNo){
        int result = bookService.deleteByBookNo(bookNo);
        if (result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed("按照bookNo删除书籍失败");
        }
    }

    /**根据bookNo获取一本书籍
     *
     * @param bookNo
     * @return
     */
    @RequestMapping("/getBookByNo")
    public ResultVO getBookByNo(String bookNo){
        Book book = bookService.getBookByNo(bookNo);
        return ResultVO.success(book);
    }

    /**书籍借出时调用
     *
     * @param bookNo
     * @return
     */
    @RequestMapping("/bookBorrow")
    public ResultVO bookBorrow(String bookNo){
       int result =
               bookService.updateBookStatus2(bookNo, BookConstants.BOOK_STATUS_YJC);
        if (result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed("书籍状态更新失败");
        }
    }

    /**书籍归还时调用
     *
     * @param bookNo
     * @return
     */
    @RequestMapping("/bookReturn")
    public ResultVO bookReturn(String bookNo){
        int result =
                bookService.updateBookStatus2(bookNo, BookConstants.BOOK_STATUS_KJC);
        if (result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed("书籍状态更新失败");
        }
    }


}





















