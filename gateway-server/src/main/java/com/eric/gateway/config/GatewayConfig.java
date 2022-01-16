package com.eric.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Configuration
public class GatewayConfig {
    Logger log= LoggerFactory.getLogger(this.getClass());
    @PostConstruct
    public void init(){
        BlockRequestHandler blockRequestHandler=new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable e) {
                HashMap<String,String> map=new HashMap<>();
                map.put("code", HttpStatus.TOO_MANY_REQUESTS.toString());
                log.info("异常信息："+e);
                String message="";
                if(e instanceof FlowException){
                    message="接口降级了";
                }else if(e instanceof DegradeException){
                    message="服务降级了";
                }else if(e instanceof ParamFlowException){
                    message="热点参数限流了";
                }else if(e instanceof SystemBlockException){
                    message="触发系统保护规则了";
                }else if(e instanceof AuthorityException){
                    message="服务授权不通过";
                }
                map.put("message", message);
                return ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }
}
