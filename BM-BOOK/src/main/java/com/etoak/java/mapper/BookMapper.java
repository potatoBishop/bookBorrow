package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Book;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
