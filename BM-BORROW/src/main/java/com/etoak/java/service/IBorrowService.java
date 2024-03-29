package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Borrow;
import org.springframework.stereotype.Service;
import java.util.List;

/***
 * @Author 高俊 QQ:1120934832
 * @Slogan 易途科技，精英启航
 */
@Service
public interface IBorrowService extends IService<Borrow> {

    boolean borrowBook(Integer userId,String bookNo,Integer duration);
    boolean borrowBookFeign(Integer userId,String bookNo,Integer duration);
    boolean returnBook(String bookNo);
//    List<Book> selectBookByRestrict(String restriction);    // 用逗号隔开各个条件
}
