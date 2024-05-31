package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Donate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/***
 * @Author 高俊 QQ:1120934832
 * @Slogan 易途科技，精英启航
 */
@Mapper
public interface DonateMapper extends BaseMapper<Donate> {

    int deleteByDonatorId(@Param(value = "donateId") int donateId);
    int updateDonateState(@Param(value = "newDonateState") int newDonateState,
                          @Param(value = "donateId") int donateId);

}
