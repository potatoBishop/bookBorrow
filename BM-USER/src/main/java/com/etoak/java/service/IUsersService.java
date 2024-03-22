package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Users;
import com.etoak.java.vo.ResultVO;
import com.etoak.java.vo.UsersVO;

import java.util.List;

// monaco字体
// 接口命名规范 I开头 + 大写驼峰
public interface IUsersService extends IService<Users> {
    /**
     * 抽象方法 没有方法体
     * JDK1.8之后可以有非抽象方法
     * 1.新增用户信息
     * 2.条件查询用户列表
     * 3.主键id删除用户
     * 4.更新用户信息
     * 5.ban用户信息
     * 、、
     * 6.根据id查询用户信息
     * 7.查询所有信用等级小于3的用户list
     *
     */

    /**
     * interface 中  public 修饰符是黑色的 没用，写不写是一样的
     * 在 interface 中 默认所有抽象方法都是public修饰
     * 抽象方法的目的 ： 等着别人去实现, 为了方便接口中方法的实现
     * @return
     */
    public int addUser(Users users);
    public int deleteById(Integer id);

    /**更新用户信息下的接口
     *
     * @param users
     * @return
     */
    public int updateUser(Users users);

    /**
     * 查询用户列表
     */
    public List<UsersVO> getUsersByParam(UsersVO usersVO);


    /**禁用指定id用户信息
     *
     */
    public int blockUser(Integer id);
    public Users getUserById(Integer id);
    public Integer getUserBlockStatus(Integer id);
    public ResultVO updateUserCreditLevel(Integer id, Integer levelNumber);

}
