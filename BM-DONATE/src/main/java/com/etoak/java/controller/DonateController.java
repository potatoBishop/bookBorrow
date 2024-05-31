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
    DonateServiceImpl donateService;
    // 如果feign接口注入时提示红线, 无法注入，则需要检查有么有开启spingCloud的feign支持
    @Autowired
    IUserServiceFeign userServiceFeign;
    @Autowired
    IBookServiceFeign bookServiceFeign;

    /**
     * 1.新增捐献记录
     * @param donate
     * @return
     */
    @RequestMapping("/addDonate")
    public ResultVO addDonate(Donate donate) {
        return ResultVO.success(1);
    }

    /**
     * 2.删除捐赠记录（假删除
     * @param donateId
     * @return
     */
    @RequestMapping("/deleteDonate")
    public ResultVO deleteDonate(int donateId) {
        return ResultVO.success(1);
    }

    /**
     * 3.更新捐赠记录
     * @param donate
     * @return
     */
    @RequestMapping("/updateDonate")
    public ResultVO updateDonate(Donate donate) {
        return ResultVO.success(1);
    }

    /**
     * 4.查询捐赠记录（仅is_delete == 0
     * @param donatorId
     * @return
     */
    @RequestMapping("/selectDonate")
    public ResultVO selectDonate(int donatorId) {
        return ResultVO.success(1);
    }

    /**
     * 5.确认捐赠
     * @param donateId
     * @return
     */
    @RequestMapping("/confirmDonate")
    public ResultVO confirmDonate(int donateId) {
        return ResultVO.success(1);
    }

    /**
     * 6.驳回捐赠
     * @param donateId
     * @return
     */
    @RequestMapping("/rejectDonate")
    public ResultVO rejectDonate(int donateId) {
        return ResultVO.success(1);
    }

    /**
     * 7.使用积分兑换书籍
     * @param donateId
     * @param userId
     * @return
     */
    @RequestMapping("/redeemDonate")
    public ResultVO redeemBookByPoints(int donateId, int userId) {
        return ResultVO.success(1);
    }

}