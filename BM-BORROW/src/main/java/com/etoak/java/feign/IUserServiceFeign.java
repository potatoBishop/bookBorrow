package com.etoak.java.feign;

import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Feign 参数
 * 1.value = 在nacos服务中心注册的服务名, 注意不是模块和项目名字
 *
 * 2.
 */

@FeignClient(value = "bm-user-service", path = "/users")
public interface IUserServiceFeign {
    /**
     * 原则就是：
     *      通过feign注解中的value值+抽象方法上的RequestMapping可以指向
     *      我们需要调用的其他服务的接口
     *
     * @ RequestParam value与用户服务中参数名一致
     * @param userId
     * @return
     */
    @RequestMapping(value = "/blockStatus")
    ResultVO getUserBlockStatus(@RequestParam(value = "id") Integer userId);
    @RequestMapping(value = "/updateUserCreditLevel")
    ResultVO updateUserCreditLevel(@RequestParam(value = "id") Integer userid,
                                   @RequestParam(value = "levelNumber") Integer levelNumber);
}


