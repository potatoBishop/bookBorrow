package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Book;
import com.etoak.java.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IBookService extends IService<Book> {
    public List<Book> selectAllBook();

    public Integer checkBookStatus(String bookNo);

    public boolean updateDurability(String bookNo, Integer durability);

    public boolean updateBookStatus(String bookNo, Integer status);

    // 教学
    int addBook(Book book);
    int updateBook(Book book);
    int deleteByBookNo(String bookNo);
    int deleteById(Integer id);
    List<Book> getList(Book book);
    Book getBookByNo(String bookNo);

    int updateBookStatus2(String bookNo, Integer status);
}
