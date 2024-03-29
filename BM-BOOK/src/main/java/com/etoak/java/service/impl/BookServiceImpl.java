package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Book;
import com.etoak.java.mapper.BookMapper;
import com.etoak.java.service.IBookService;
import com.etoak.java.utils.StringUtils;
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

    /**根据book_no更新书籍磨损
     *
     * @param bookNo
     * @param durability
     * @return
     */
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

    /**根据book_no更新status
     *
     */
    public boolean updateBookStatus(String bookNo, Integer status){
        if( 0 <= status && status >= 3  ){
            System.out.println("status数据非法！");
            return false;
        }
        UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("book_no",bookNo);
        Book newBook = new Book();
        newBook.setStatus(status);
        int changeNum = bookMapper.update(newBook, updateWrapper);
        if( changeNum > 0 ){
            return true;
        }else {
            System.out.println("update时出现错误0");
            return false;
        }
    }

    // 教学==================================================================
    @Override
    public int addBook(Book book) {
        return 0;
    }

    @Override
    public int updateBook(Book book) {
        return 0;
    }

    @Override
    public int deleteByBookNo(String bookNo) {
        QueryWrapper param = new QueryWrapper<>();
        param.eq("book_no", bookNo);
        return bookMapper.delete(param);
    }

    @Override
    public int deleteById(Integer id) {

        return bookMapper.deleteById(id);
    }

    /**
     * 多条件组合查询
     * @param book
     * @return
     */
    @Override
    public List<Book> getList(Book book) {
        QueryWrapper param = new QueryWrapper();
        if(StringUtils.isNotEmpty(book.getBookName())){
            param.like("book_name",book.getBookName());
        }
        if(StringUtils.isNotEmpty(book.getBookNo())){
            param.eq("book_no",book.getBookNo());
        }
        /**
         * 前端传送的查询格式  “物理，国内，中文”
         * 数据库内          “国内，物理，中文，高级，比较难”
         */
        if( StringUtils.isNotEmpty(book.getBookLable()) ){
            // 如何将字符串按逗号分割
            String bookLable = book.getBookLable();
            String[]  lableArray = bookLable.split(",");
            for( String s : lableArray ){
                if( StringUtils.isNotEmpty(s) ){
                    param.like("book_lable", s);
                }
            }
        }
        return bookMapper.selectList(param);
    }

    @Override
    public Book getBookByNo(String bookNo) {
        return bookMapper.getBookByNo(bookNo);
    }


    @Override
    public int updateBookStatus2(String bookNo, Integer status) {
        return bookMapper.updateBookStatus2(bookNo, status);
    }

}


















