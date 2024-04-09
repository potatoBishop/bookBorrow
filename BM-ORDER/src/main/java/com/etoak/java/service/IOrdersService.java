package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Orders;
import com.etoak.java.vo.ResultVO;

import java.math.BigDecimal;
import java.util.List;

public interface IOrdersService extends IService<Orders> {
    /**
     * 添加订单
     * @param order
     * @return
     */
    public int addOrders(Orders order);
    /**
     * 根据id删除订单
     * @param id
     * @return
     */
    public int deleteById(Integer id);
    /**
     * 通过订单号查询订单
     * @param orderNo
     * @return
     */
    public Orders selectByNo(String orderNo);
    /**
     * 多条件筛选订单
     * @param order
     * @return
     */
    public List<Orders> listOrders(Orders order);
    /**
     * 更新order
     * @param order
     * @return
     */
    public int updateOrders(Orders order);
    /**
     * 更新order的status为1 通过
     * @param orderNo
     * @return
     */
    public int approved(String orderNo);
    /**
     * 更新order的status为2 拒绝
     * @param orderNo
     * @return
     */
    public int rejected(String orderNo);
    /**
     * 根据年份计算总的花费
     * @param year
     * @return
     */
    public BigDecimal computeByYear(Integer year);
    /**
     * 根据出版社计算总的花费
     * @param publisher
     * @return
     */
    public BigDecimal computeByPublisher(String publisher);


}
