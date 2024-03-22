package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Borrow;
import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.mapper.BorrowMapper;
import com.etoak.java.service.IBorrowService;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Calendar;
import java.util.Date;

/***
 * @Author 高俊 QQ:1120934832
 * @Slogan 易途科技，精英启航
 */
@Service
public class BorrowServiceImpl
        extends ServiceImpl<BorrowMapper, Borrow>
        implements IBorrowService {

    @Autowired
    BorrowMapper borrowMapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    IUserServiceFeign userServiceFeign;

    /**
     * 用户借书
     * @param userId
     * @param bookNo
     * @param duration
     * @return
     */
    @Override
    public boolean borrowBook(Integer userId, String bookNo, Integer duration) {
        // 1.判断用户是否被禁用了
//        String baseUrl = "http://localhost:8083/users/blockStatus";
        String baseUrl = "http://bm-user-service/users/blockStatus";
        // URL构建器 -> 携带请求参数
        UriComponentsBuilder builder
                = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("id",userId);
        // 构建器 构建 URL
        String url = builder.toUriString();
        // 发送请求
        ResultVO<Integer> blockStatusResult = restTemplate.getForObject(url, ResultVO.class);
        System.out.println("查询用户禁用状态：" + blockStatusResult);
        //查询用户禁用状态
        // Integer blockStatus = (Integer) blockStatusResult.getData();  可以，但是蠢
        Integer blockStatus = blockStatusResult.getData();
        if( blockStatus == 1 ){
            //用户被禁用了
            System.out.println("用户已被禁用");
            return false;
        }
        // 2.用户要借的书是否还在（在库？已借出）
        Integer bookStatus = 0; //在库
        if( bookStatus == 1 ){
            //图书已借出
            System.out.println("图书已经借出");
            return false;
        }
        // 3.新增一条借阅记录 borrow
        Borrow newBorrow = new Borrow();
        newBorrow.setUserId(userId);
        newBorrow.setBookNo(bookNo);
        newBorrow.setDuration(duration);
        newBorrow.setCreateTime(new Date());
        newBorrow.setStuNo("2012114514");
        Integer flag = borrowMapper.insert(newBorrow);
        System.out.println("新增一条记录"+ flag);
        // 4.将书修改为已借出状态

        return true;
    }

    /**
     * 还书
     * @param bookNo
     * @return
     */
    @Override
    public boolean returnBook(String bookNo) {
        // 查借阅记录
        QueryWrapper param = new QueryWrapper();      // 组装查询器
        param.eq("book_no", bookNo);
        // 根据create_time降序排列
        param.orderBy(false, false, "create_time");
        Borrow lastBorrow = borrowMapper.selectOne(param);
//        System.out.println(lastBorrow);//查询到最新记录
        Integer duration = lastBorrow.getDuration();
        Date createTime = lastBorrow.getCreateTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createTime);
        calendar.add(Calendar.DATE, duration);
        // 信用等级更新数值
        Integer levelNumber = 0;
        if(calendar.getTime().after(new Date())){
            // 正常归还
            levelNumber = 1;
//            Borrow newBorrow = lastBorrow;
            Borrow newBorrow = new Borrow();
            newBorrow.setId(lastBorrow.getId());
            newBorrow.setStatus(1);
            borrowMapper.updateById(newBorrow);

        } else {
            // 逾期归还
            levelNumber = -1;
            Borrow newBorrow = new Borrow();
            newBorrow.setId(lastBorrow.getId());
            newBorrow.setStatus(2);
            newBorrow.setComment("该用户已归还书籍，但是超出应归还日期");
            borrowMapper.updateById(newBorrow);
        }

        String baseUrl = "http://bm-user-service/users/updateUserCreditLevel";
        // URL构建器 -> 携带请求参数
        UriComponentsBuilder builder
                = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("id",lastBorrow.getUserId())
                .queryParam("levelNumber", levelNumber);
        // 构建器 构建 URL
        String url = builder.toUriString();
        // 发送请求  上方已经无用
       // ResultVO<Integer> blockStatusResult = restTemplate.getForObject(url, ResultVO.class);
        ResultVO<Integer> blockStatusResult = userServiceFeign.updateUserCreditLevel(lastBorrow.getUserId(), levelNumber);
        System.out.println("更新用户信用等级：" + blockStatusResult);
        return false;
//        Date today = new Date();
//        Date pre   = lastBorrow.getCreateTime();
//        long milliseconds1 = today.getTime();
//        long milliseconds2 = pre.getTime();
//        long diff = milliseconds2 - milliseconds1;
//        long daysBetween = diff / (24 * 60 * 60 * 1000);        // 获取时间间隔
//
//        String baseUrl = "http://bm-user-service/users/updateUserCreditLevel";
//        UriComponentsBuilder builder
//                = UriComponentsBuilder.fromHttpUrl(baseUrl)
//                .queryParam("userId",lastBorrow.getUserId(),"");
//        // 构建器 构建 URL
//        String url = builder.toUriString();
//        // 发送请求
//        ResultVO<Integer> blockStatusResult = restTemplate.getForObject(url, ResultVO.class);
//        if( daysBetween > (long)lastBorrow.getDuration() ){
//            // 逾期
//            // 更新借阅记录
//            lastBorrow.setComment("无备注");
//            lastBorrow.setStatus(2);         //0：借阅中  1：已归还   2：已逾期   3：无法归还
//            lastBorrow.setReturnTime(today);
//            borrowMapper.updateById(lastBorrow);
//            // 用户等级修改
//
//        } else {
//            // 正常
//            // 更新借阅记录
//            lastBorrow.setComment("无备注");
//            lastBorrow.setStatus(1);         //0：借阅中  1：已归还   2：已逾期   3：无法归还
//            lastBorrow.setReturnTime(today);
//            borrowMapper.updateById(lastBorrow);
//            // 用户等级修改
//        }
//
//        return false;
    }

    /**
     * 借阅服务中：
     * 实现根据用户id查询禁用状态
     */
    @Override
    public boolean borrowBookFeign(Integer userId, String bookNo, Integer duration) {
        // 1.判断用户是否被禁用了
//        String baseUrl = "http://localhost:8083/users/blockStatus";
        //String baseUrl = "http://bm-user-service/users/blockStatus";
        // URL构建器 -> 携带请求参数
        //UriComponentsBuilder builder
        //        = UriComponentsBuilder.fromHttpUrl(baseUrl)
        //        .queryParam("id",userId);
        // 构建器 构建 URL
        //String url = builder.toUriString();
        // 发送请求
        //ResultVO<Integer> blockStatusResult = restTemplate.getForObject(url, ResultVO.class);
        ResultVO<Integer> blockStatusResult = userServiceFeign.getUserBlockStatus(userId);
        System.out.println("查询用户禁用状态：" + blockStatusResult);
        //查询用户禁用状态
        // Integer blockStatus = (Integer) blockStatusResult.getData();  可以，但是蠢
        Integer blockStatus = blockStatusResult.getData();
        if( blockStatus == 1 ){
            //用户被禁用了
            return false;
        }
        // 2.用户要借的书是否还在（在库？已借出）
        Integer bookStatus = 0; //在库
        if( bookStatus == 1 ){
            //图书已借出
            return false;
        }
        // 3.新增一条借阅记录 borrow
        Borrow newBorrow = new Borrow();
        newBorrow.setUserId(userId);
        newBorrow.setBookNo(bookNo);
        newBorrow.setDuration(duration);
        newBorrow.setCreateTime(new Date());
        newBorrow.setStuNo("2012114514");
        borrowMapper.insert(newBorrow);

        // 4.将书修改为已借出状态

        return false;
    }
}