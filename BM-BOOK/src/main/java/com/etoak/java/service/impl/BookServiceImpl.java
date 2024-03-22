package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Book;
import com.etoak.java.mapper.BookMapper;
import com.etoak.java.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl
        extends ServiceImpl<BookMapper, Book>
        implements IBookService {

    @Autowired
    BookMapper bookMapper;

    /**查看所有书籍
     *
     * @return
     */
    public List<Book> selectAllBook(){
        QueryWrapper wrapper = new QueryWrapper();
        List<Book> result = bookMapper.selectList(wrapper);
        for( int i = 0; i <= result.size(); i++ ){
            System.out.println(result.indexOf(i));
        }
        if( result != null ){
            return result;
        }else {
            return null;
        }
    }


    /**根据book_no获取status
     *
     * @param bookNo
     * @return
     */
    @Override
    public Integer checkBookStatus(String bookNo) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("book_no", bookNo);
        Book selectedBook = bookMapper.selectOne(wrapper);
        if ( selectedBook != null ){
            return selectedBook.getStatus();
        } else {
            return null;
        }
    }

    @Override
    public boolean updateDurability(String bookNo, Integer durability) {
        if( 0 <= durability && durability >= 5  ){
            System.out.println("durability数据非法！");
            return false;
        }
//        QueryWrapper<Book> wrapper = new QueryWrapper<>();
//        wrapper.eq("book_no", bookNo);
//        Book newBook = bookMapper.selectOne(wrapper);
//        newBook.setDurability(durability);
//        wrapper.setEntity(newBook);
//        bookMapper.update(wrapper);
        //把名字为rhb的用户年龄更新为18，其他属性不变
        UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("book_no",bookNo);
        Book newBook = new Book();
        newBook.setDurability(durability);
        int changeNum = bookMapper.update(newBook, updateWrapper);
        if( changeNum > 0 ){
            return true;
        }else {
            System.out.println("update时出现错误0");
            return false;
        }
    }
}
