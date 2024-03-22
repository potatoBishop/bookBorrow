package com.etoak.java.service;

import com.etoak.java.entity.Book;
import com.etoak.java.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IBookService {
    public List<Book> selectAllBook();

    public Integer checkBookStatus(String bookNo);

    public boolean updateDurability();
}
