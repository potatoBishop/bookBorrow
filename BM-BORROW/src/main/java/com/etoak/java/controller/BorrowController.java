package com.etoak.java.controller;

import com.etoak.java.entity.Borrow;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.service.impl.BorrowServiceImpl;
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
public class BorrowController {

    @Autowired
    BorrowServiceImpl borrowService;
    // 如果feign接口注入时提示红线, 无法注入，则需要检查有么有开启spingCloud的feign支持
    @Autowired
    IUserServiceFeign userServiceFeign;
    @Autowired
    IBookServiceFeign bookServiceFeign;


    /**借书
     *
     * @param userId
     * @param bookNo
     * @param duration
     * @return
     */
    @RequestMapping("/borrowBook")
    public ResultVO borrowBook(Integer userId,
                               String bookNo,
                               Integer duration){
        boolean result = borrowService.borrowBook(userId,bookNo,duration);

        if(result){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed("借阅失败");
        }

    }

    /**借书 但是feign
     *
     * @param userId
     * @param bookNo
     * @param duration
     * @return
     */
    @RequestMapping("/borrowBookFeign")
    public ResultVO borrowBookFeign(Integer userId,
                               String bookNo,
                               Integer duration){
        boolean result = borrowService.borrowBookFeign(userId,bookNo,duration);

        if(result){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed("借阅失败");
        }

    }


    /** 归还接口
    * 参数 ： bookNo
    * 借阅记录 --> 最近的借阅记录 -->逾期？正常？
    *      正常-->更新借阅记录-->正常  用户信用等级升级
    *      逾期-->更新借阅记录-->逾期  用户信用等级降级 最低至0
    *          如果本身已经是0 -->  顺便禁用他的账户
    *
    */
    @RequestMapping("/returnBook")
    public ResultVO returnBook( String bookNo ){
        // 根据 bookNo 查记录
        return ResultVO.success(borrowService.returnBook(bookNo));
    }


    /**
     * 根据id查询用户禁用状态
     * @param userId
     * @return
     */
    @RequestMapping("/getUserBlockStatus")
    public ResultVO getUserBlockStatus(Integer userId){
        ResultVO result = userServiceFeign.getUserBlockStatus(userId);
        return result;
    }
}