package com.etoak.java.controller;

import com.etoak.java.entity.Orders;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.mapper.OrdersMapper;
import com.etoak.java.service.impl.OrdersServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/orders")
//@RefreshScope                       // 变量改变时刷新相关实体类
public class OrdersController {

    @Autowired
    OrdersServiceImpl ordersService;

//    @Autowired
//    IBookServiceFeign bookServiceFeign;

//    @Value("${order.header}")
//    private String orderNoHeader;     //前缀 nacos作用

    /**
     * 添加订单
     * @param order
     * @return
     */
    @RequestMapping("/addOrders")
    public ResultVO addOrders(Orders order){
        int result = ordersService.addOrders(order);
        if( result > 0 ){
            return ResultVO.success("添加用户成功"+result);
        } else {
            return ResultVO.failed("添加用户失败");
        }
    }


    /**
     * 根据id删除订单
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public ResultVO deleteById(Integer id){
        int result = ordersService.deleteById(id);
        if( result > 0 ){
            return ResultVO.success("删除用户成功"+result);
        } else {
            return ResultVO.failed("删除用户失败");
        }
    }


    /**
     * 通过订单号查询订单
     * @param orderNo
     * @return
     */
    @RequestMapping("/selectByNo")
    public ResultVO selectByNo(String orderNo){
        Orders result = ordersService.selectByNo(orderNo);
        if( result != null ){
            return ResultVO.success(result);
        } else {
            return ResultVO.failed("查询失败，未找到相关订单");
        }
    }


    /**
     * 多条件筛选订单
     * @param order
     * @return
     */
    @RequestMapping("/listOrders")
    public ResultVO listOrders(Orders order){
        List<Orders> result = ordersService.listOrders(order);
        if( !result.isEmpty() ){
//            System.out.println(result);
            return ResultVO.success(result);
        } else {
            return ResultVO.failed("查询失败，结果为空");
        }
    }


    /**
     * 更新order
     * @param order
     * @return
     */
    @RequestMapping("/updateOrders")
    public ResultVO updateOrders(Orders order){
        int result = ordersService.updateOrders(order);
        if( result > 0 ){
            return ResultVO.success("更新用户成功"+result);
        } else {
            return ResultVO.failed("更新用户失败");
        }
    }


    /**
     * 更新order的status为1 通过
     * @param orderNo
     * @return
     */
    @RequestMapping("/approved")
    public ResultVO approved(String orderNo){
        int result = ordersService.approved(orderNo);
        if( result == 0 ) {
            return ResultVO.failed("修改order表的statu=1失败 添加book失败");
        } else  if( result == 1 ){
            return ResultVO.success("修改order的statu=1成功 添加book成功");
        } else if (result == 2){
            return ResultVO.failed("修改order的statu=1成功  添加book失败");
        } else {
            return ResultVO.failed("未知错误");
        }
    }


    /**
     * 更新order的status为2 拒绝
     * @param orderNo
     * @return
     */
    @RequestMapping("/rejected")
    public ResultVO rejected(String orderNo){
        int result = ordersService.rejected(orderNo);
        if( result > 0 ){
            return ResultVO.success("修改order的statu=2成功"+result);
        } else {
            return ResultVO.failed("修改order的statu=2失败");
        }
    }


    /**
     * 根据年份计算总的花费
     * @param yearNeed
     * @return
     */
    @RequestMapping("/computeByYear")
    public ResultVO computeByYear(Integer yearNeed){
        BigDecimal result = ordersService.computeByYear(yearNeed);
        if( result != null ){
            return ResultVO.success(result);
        } else {
            return ResultVO.failed("获取根据年份计算的总花费：失败");
        }
    }


    /**
     * 根据出版社计算总的花费
     * @param publisher
     * @return
     */
    @RequestMapping("/computeByPublisher")
    public ResultVO computeByPublisher(String publisher){
        BigDecimal result = ordersService.computeByPublisher(publisher);
        if( result != null ){
            return ResultVO.success(result);
        } else {
            return ResultVO.failed("获取根据出版社计算的总花费：失败");
        }
    }



}
