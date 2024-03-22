package com.etoak.java.feign;


import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bm-book-service", path = "/book")
public interface IBookServiceFeign {

    @RequestMapping(value = "/checkBookStatus")
    ResultVO checkBookStatus(@RequestParam(value = "bookNo") String bookNo);
}
