package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    int updateOrderStatus(
            @Param(value = "orderNo") String orderNo,
            @Param(value = "status") Integer status
    );
    BigDecimal computeByYear(
            @Param(value = "year") int year
    );
    BigDecimal computeByPublisher(
            @Param(value = "publisher") String publisher
    );

}
