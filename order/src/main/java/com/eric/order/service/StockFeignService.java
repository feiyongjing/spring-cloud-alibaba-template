package com.eric.order.service;

import com.eric.order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-server",
        path = "/stock",
        configuration = FeignConfig.class
//        fallback = StockRemoteClientFallBack.class,
//        fallbackFactory = StockRemoteClientFallBackFactory.class
)
public interface StockFeignService {
    @RequestMapping("/reduct")
    String reduct(@RequestParam("productId") Long productId,
                  @RequestParam("amount") Integer amount);

    @RequestMapping("/test")
    String test();
}
