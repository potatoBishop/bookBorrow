package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Donate;
import com.etoak.java.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * @Author 高俊 QQ:1120934832
 * @Slogan 易途科技，精英启航
 */
@Service
public interface IDonateService extends IService<Donate> {
    // 1.新增捐献记录
    int addDonate(Donate donate);

    // 2.删除捐赠记录（假删除
    int deleteDonate(int donateId);

    // 3.更新捐赠记录
    int updateDonate(Donate donate);

    // 4.查询捐赠记录（仅is_delete == 0  根据用户id
    List<Donate> selectDonate(int donatorId);

    // 5.确认捐赠
    int confirmDonate(int donateId);
        // set donate_state = 1
        // bookOpenFeign新增书籍
        // userOpenFeign增加一个user的points

    // 6.驳回捐赠
    int rejectDonate(int donateId);
        // set donate_state = 0

    // 7.使用积分 书籍编号、兑换人学号
    int redeemBookByPoints(int donateId, int userId);
        // 书籍在库？ 所需积分  返回一个book实体
        // 查询该兑换人积分余额 返回一个user实体
        // 若余额充足
            // 扣除积分（OpenFeign）
            // 将书籍状态修改为 已被兑换（OpenFeign）
        // 积分不足
            // 提示请求拒绝

}
