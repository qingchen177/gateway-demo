package com.example.springbootgateway.config;

/**
 * @author qingchen
 * @date 12/5/2023 上午 11:10
 */

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String accessToken = request.getHeaders().getFirst("token");
        if ("xxx".equals(accessToken)) {
            chain.filter(exchange);
            return chain.filter(exchange);
        } else {
            return loginResponse(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public static Mono<Void> loginResponse(ServerWebExchange exchange) {
        Map<String, Object> resultJson = new HashMap<>();
        resultJson.put("code", 401);
        resultJson.put("message", "请重新登陆授权");
        resultJson.put("status", 401);
        ServerHttpResponse response = exchange.getResponse();
        byte[] bytes = resultJson.toString().getBytes();
        response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }
}


