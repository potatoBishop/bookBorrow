package com.etoak.java.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Users;
import com.etoak.java.mapper.UsersMapper;
import com.etoak.java.service.IUsersService;
import com.etoak.java.vo.ResultVO;
import com.etoak.java.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl
    extends ServiceImpl<UsersMapper, Users>
    implements IUsersService {

    /**
     * 依赖注入
     * 控制反转
     * 对象的创建掌握在程序员手里， 接口无法创建
     * 单例模式 ？ 最简单 一个类 有且只有 一个对象 太阳
     *
     * 对象的创建交给springboot
     * 1.交给springboot
     * 2.通过@ auto
     * @param users
     * @return
     */

    @Autowired
    UsersMapper usersMapper;


    @Override
    public int plusPoints(int userId, int plus) {
        Users users = usersMapper.selectById(userId);
        users.setPoints( users.getPoints() + plus );
        int result = usersMapper.updateById(users);
        return result;
    }

    @Override
    public int checkPoints(int userId) {
        Users users = usersMapper.selectById(userId);
        return users.getPoints();
    }

    @Override
    public int addUser(Users users) {
        //新增用户 int 类型返回值 代表 影响行数
        int result =  usersMapper.insert(users);
        return result;
    }

    /**
     * 根据主键id删除用户信息
     *
     */
    @Override
    public int deleteById(Integer id) {
        int result =  usersMapper.deleteById(id);
        return result;
    }
    /**
     * 根据主键id更新用户信息
     *
     */
    @Override
    public int updateUser(Users users) {
        int result = usersMapper.updateById(users);
        return result;
    }

    /**多条件查询
     *  按照name college 模糊查询
     * @param usersVO
     * @return
     */
    @SentinelResource(value = "getUserListService")
    @Override
    public List<UsersVO> getUsersByParam(UsersVO usersVO) {
        QueryWrapper wrapper = new QueryWrapper();
        if(usersVO.getName()!=null && !usersVO.getName().equals("")){
            wrapper.like("name", usersVO.getName());
        }
        if(usersVO.getCollege()!=null && !usersVO.getCollege().equals("")){
            wrapper.like("college", usersVO.getCollege());
        }
        if(usersVO.getGender()!=null){
            wrapper.eq("gender", usersVO.getGender());
        }
        if( usersVO.getCreditLevelMax()!=null ){    //小于等于
            wrapper.le("credit_level", usersVO.getCreditLevelMax());
        }
        if( usersVO.getCreditLevelMin()!=null ){    //大于等于
            wrapper.ge("credit_level", usersVO.getCreditLevelMin());
        }

        return usersMapper.selectList(wrapper);
    }


    /**
     * 根据id block 用户
     * @param id
     * @return
     */
    @Override
    public int blockUser(Integer id) {
        return usersMapper.blockUser(id);
    }

    /**
     * 根据id返回一个用户的信息
     * @param id
     * @return
     */
    @Override
    public Users getUserById(Integer id) {
        return usersMapper.selectById(id);
    }

    /**根据id查询用户禁用状态
     *
     * @param id
     * @return
     */
    @Override
    public Integer getUserBlockStatus(Integer id) {
        Users users = usersMapper.selectById(id);
        return users.getIsBlock();
    }

    @Override
    public ResultVO updateUserCreditLevel(Integer id, Integer levelNumber) {
        // 查询当前用户信息
        Users users = getUserById(id);
        if( users == null ){
            return ResultVO.failed("未查询到该用户");
        }
//        Integer res = usersMapper.updateUserCreditLevel(id, levelNumber);
        String userCreditLevel = users.getCreditLevel();
        if( Integer.parseInt(userCreditLevel) <= 0 && levelNumber <0 ){
            // 信用等级为0， 不再降级，直接禁用账户
            usersMapper.blockUser(id);
            return ResultVO.failed("用户已被直接封禁");
        }
        Integer result = usersMapper.updateUserCreditLevel(id, levelNumber);
        return ResultVO.success("用户信用等级被修改为"+result);

    }

    /**
     * 公共资源
     * @param from
     * @return
     */
    @SentinelResource(value = "commonResource")
    public String commonResource(String from) {
        return "公共资源被调用了， 调用来源"+from;
    }
}
