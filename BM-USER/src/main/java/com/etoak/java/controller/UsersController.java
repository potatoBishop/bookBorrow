package com.etoak.java.controller;

import com.etoak.java.entity.Users;
import com.etoak.java.service.impl.UsersServiceImpl;
import com.etoak.java.vo.ResultVO;
import com.etoak.java.vo.UsersVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RefreshScope                       // 变量改变时刷新相关实体类
public class UsersController {
    @Autowired
    UsersServiceImpl usersService;

    @Value("${qdu.stu-no}")
    private String stuNoPrefix;     //前缀 nacos作用

    @RequestMapping("/prefix")
    public void getStuNoPrefix(){
        System.out.println("从配置中心读取配置"+stuNoPrefix);
    }


    /**
     * 请求的URL
     * http://localhost:8083/users/add
     *      *                     /controller类上的requestMapping注解/方法上的
     * @param users
     * @return
     */
    @RequestMapping("/add")
    public ResultVO addUser(Users users){
        int result = usersService.addUser(users);
        if( result > 0 ){
            return ResultVO.success(null);
        } else {
            return ResultVO.failed("新增用户信息失败！");
        }
    }

    /**
     * http://localhost:8083/users/add
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public ResultVO deleteById(Integer id){
        int result = usersService.deleteById(id);
        if( result > 0 ){
            return ResultVO.success(null);
        } else {
            return ResultVO.failed("删除用户信息失败");
        }
    }

    /** update用户信息的接口
     * http://localhost:8083/users/update
     */
    @RequestMapping("update")
    public ResultVO updateUser(Users users){
        int result = usersService.updateUser(users);
        if( result > 0 ){
            return ResultVO.success(null);
        } else {
            return ResultVO.failed("更新用户信息失败");
        }
    }

    /**多条件组合查询用户列表
     *
     * @param usersVO
     * @return
     */
    @RequestMapping("/list")
    public ResultVO getUserList(UsersVO usersVO){
        List<UsersVO> resultList = usersService.getUsersByParam(usersVO);
        return ResultVO.success(resultList);
    }


    /**
     * 根据id禁用用户信息
     * @param id
     * @return
     */
    @RequestMapping("/block")
    public ResultVO blockUser(Integer id){
        int result = usersService.blockUser(id);
        if( result > 0 ){
            return ResultVO.success(null);
        } else {
            return ResultVO.failed("禁用用户信息失败 或 用户已经是禁用状态");
        }
    }

    /**根据用户id查询用户信息
     *
     * @param id
     * @return user
     */

    @RequestMapping("/selectById")
    public ResultVO getUserById(Integer id){
        Users users = usersService.getUserById(id);
        return ResultVO.success(users);
    }
    @Value("${server.port}")
    String port;

    //根据用户id查询禁用状态
    @RequestMapping("/blockStatus")
    public ResultVO getUserBlockStatus(Integer id){
        System.out.println("运行在端口号: " + port + "的user-service被调用了");
        return ResultVO.success(usersService.getUserBlockStatus(id));
    }

    // 提升或下降用户信用等级
    @RequestMapping("/updateUserCreditLevel")
    public ResultVO updateUserCreditLevel(Integer id, Integer levelNumber ){
        return usersService.updateUserCreditLevel(id, levelNumber);
    }
}
