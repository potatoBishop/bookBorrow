package com.etoak.java.controller;

import com.etoak.java.entity.Donate;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.service.impl.DonateServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @Author 高俊 QQ:1120934832
 * @Slogan 易途科技，精英启航
 */
@RestController
@RequestMapping("/borrow")
public class DonateController {

    @Autowired
    DonateServiceImpl borrowService;
    // 如果feign接口注入时提示红线, 无法注入，则需要检查有么有开启spingCloud的feign支持
    @Autowired
    IUserServiceFeign userServiceFeign;
    @Autowired
    IBookServiceFeign bookServiceFeign;


    
}