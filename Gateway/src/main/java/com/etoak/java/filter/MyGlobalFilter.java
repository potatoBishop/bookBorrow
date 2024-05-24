package com.etoak.java.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class MyGlobalFilter implements GlobalFilter {
    @Override public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 在此处编写全局过滤器的逻辑
        //1、获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        //2、获取请求的资源路径
        String path = request.getURI().getPath();
        System.out.println(path);
//        if(!"/user/list".equals(path)){
//            String token = request.getHeaders().getFirst("token");
//            if(token == null || token.isEmpty()){
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//        }
        return chain.filter(exchange);
    }
}
