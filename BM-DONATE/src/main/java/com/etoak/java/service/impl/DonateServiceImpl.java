package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Donate;
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
    DonateMapper DonateMapper;
    @Autowired
    IUserServiceFeign userServiceFeign;
    @Autowired
    IBookServiceFeign bookServiceFeign;


    @Override
    public boolean addDonate(Donate donate) {
        return false;
    }

    @Override
    public boolean deleteDonate(int donateId) {
        return false;
    }

    @Override
    public boolean updateDonate(Donate donate) {
        return false;
    }

    @Override
    public List<Donate> checkDonate(int donatorId) {
        return null;
    }

    @Override
    public boolean confirmDonate(int donateId) {
        return false;
    }

    @Override
    public boolean rejectDonate(int donateId) {
        return false;
    }

    @Override
    public boolean redeemBookByPoints(int donateId, int userId) {
        return false;
    }
}
