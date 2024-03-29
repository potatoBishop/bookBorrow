package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface BookMapper extends BaseMapper<Book> {

    /**根据书籍编号获取书籍
     *
     * @param bookNo
     * @return
     */
    Book getBookByNo(@Param(value = "bookNo") String bookNo);

    int updateBookStatus2(@Param(value = "bookNo") String bookNo,@Param(value = "status") Integer status);
}
