package com.etoak.java.controller;

import com.etoak.java.entity.Donate;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.service.impl.DonateServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @Author 土豆真人 QQ:1972588587
 */
@RestController
@RequestMapping("/donate")
public class DonateController {

    @Autowired
    DonateServiceImpl borrowService;
    // 如果feign接口注入时提示红线, 无法注入，则需要检查有么有开启spingCloud的feign支持
    @Autowired
    IUserServiceFeign userServiceFeign;
    @Autowired
    IBookServiceFeign bookServiceFeign;

//    @RequestMapping("/borrowBook")
//    public ResultVO borrowBook(){}

    @RequestMapping("/addDonate")
    public ResultVO addDonate(Donate donate) {
        return ResultVO.success(1);
    }

    @RequestMapping("/deleteDonate")
    public ResultVO deleteDonate(int donateId) {
        return ResultVO.success(1);
    }

    @RequestMapping("/updateDonate")
    public ResultVO updateDonate(Donate donate) {
        return ResultVO.success(1);
    }

    @RequestMapping("/addDonate")
    public ResultVO checkDonate(int donatorId) {
        return ResultVO.success(1);
    }

    @RequestMapping("/addDonate")
    public ResultVO confirmDonate(int donateId) {
        return ResultVO.success(1);
    }

    @RequestMapping("/addDonate")
    public ResultVO rejectDonate(int donateId) {
        return ResultVO.success(1);
    }

    @RequestMapping("/addDonate")
    public ResultVO redeemBookByPoints(int donateId, int userId) {
        return ResultVO.success(1);
    }

}