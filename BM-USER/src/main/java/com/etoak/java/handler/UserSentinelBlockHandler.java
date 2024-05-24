package com.etoak.java.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class UserSentinelBlockHandler {
    public String blockHandler(String form, BlockException e){
        return "点击速度过快";
    }
}
