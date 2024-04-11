package com.etoak.java.feign;

import com.etoak.java.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "bm-orders-service", path = "/orders")
public interface IOrdersServiceFeign {

}