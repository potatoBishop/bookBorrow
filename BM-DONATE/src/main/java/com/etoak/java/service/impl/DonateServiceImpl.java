package com.etoak.java.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Book;
import com.etoak.java.entity.Donate;
import com.etoak.java.entity.Users;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.mapper.DonateMapper;
import com.etoak.java.service.IDonateService;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/***
 * @Author 高俊 QQ:1120934832
 * @Slogan 易途科技，精英启航
 */
@Service
public class DonateServiceImpl
        extends ServiceImpl<DonateMapper, Donate>
        implements IDonateService {

    @Autowired
    DonateMapper donateMapper;
    @Autowired
    IUserServiceFeign userServiceFeign;
    @Autowired
    IBookServiceFeign bookServiceFeign;


    @Override
    public int addDonate(Donate donate) {
        int result = donateMapper.insert(donate);
        return result;
    }

    @Override
    public int deleteDonate(int donateId) {
        int result = donateMapper.deleteByDonatorId(donateId);
        return result;
    }

    @Override
    public int updateDonate(Donate donate) {
        int result = donateMapper.updateById(donate);
        return result;
    }

    @Override
    public List<Donate> selectDonate(int donatorId) {
        QueryWrapper param = new QueryWrapper();
        param.eq("donator_id", donatorId);  // 当前用户
        param.eq("is_delete", 0);       // 未被删除记录
        List<Donate> result = donateMapper.selectList(param);
        return result;
    }

    @Override
    public int confirmDonate(int donateId) {
        int result = donateMapper.updateDonateState(1, donateId);
        Donate donate = donateMapper.selectById(donateId);
        // bookOpenFeign新增书籍
        Book book = new Book();
        bookServiceFeign.addBook(book);
        // userOpenFeign增加一个user的points
        int donatorId = donate.getDonator_id();
        int points = donate.getPoints();
        userServiceFeign.plusPoints(donatorId, points);
        return result;
    }

    @Override
    public int rejectDonate(int donateId) {
        int result = donateMapper.updateDonateState(2, donateId);
        return result;
    }

    @Override
    public int redeemBookByPoints(int donateId, int userId) {
        // 1成功 2积分不足 3书籍已经借出 4其他错误
        Donate donate = donateMapper.selectById(donateId);
        String bookNo = donate.getBookNo();
        // 书籍在库？ 所需积分  返回一个book实体
        Book book = (Book) bookServiceFeign.getBookByNo(bookNo).getData();
        int bookIsAvailable = book.getStatus();     // 书籍状态
        int pointsNeeded = book.getPoints();        // 书籍所需积分

        // 查询该兑换人积分余额 返回一个user实体
        Users users = (Users) userServiceFeign.getUserById(userId).getData();
        int pointsHad = users.getPoints();

        // 若余额充足
        if( bookIsAvailable == 0 && pointsHad >= pointsNeeded ){
            // 扣除积分（OpenFeign）
            userServiceFeign.plusPoints(userId, -pointsNeeded);
            // 将书籍状态修改为 已被兑换（OpenFeign）
            bookServiceFeign.updateBookStatus(bookNo, 1);
        } else {
            // 积分不足
            if( pointsHad < pointsNeeded ){
                return 2;
            }
            // 书籍已经借出
            if( bookIsAvailable == 1 ){
                return 3;
            }
            return 4;
        }
        return 1;
    }
}
