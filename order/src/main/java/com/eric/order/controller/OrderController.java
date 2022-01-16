package com.eric.order.controller;

import com.eric.order.generate.Order;
import com.eric.order.generate.OrderMapper;
import com.eric.order.service.StockFeignService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    StockFeignService stockFeignService;

    @Autowired
    OrderMapper orderMapper;

    @RequestMapping("/getOrder")
    public String test() throws InterruptedException {
        stockFeignService.test();
        test1("777","xxx");
        return "测试成功";
    }

    @Trace
    @Tags({@Tag(key = "test1",value = "returnedObj"),
            @Tag(key = "test1",value = "arg[0]"),
            @Tag(key = "test1",value = "arg[1]"),
    })
    public String test1(String a,String b){
        return a+b;
    }


    @GlobalTransactional
    @RequestMapping("/addOrder")
    public String addOrder(@RequestParam("productId") Long productId,
                           @RequestParam("amount") Integer amount) {
        // 根据产品id获取产品的单价和amount 下单数量计算 总金额
        // 订单数据入库
        Order order = new Order();
        order.setProductId(productId);
        order.setTotalAmount(new BigDecimal(amount).multiply(BigDecimal.valueOf(88.8)));
        order.setStatus("未支付");
        orderMapper.insertSelective(order);

        // 调用库存服务扣减库存
        String s = stockFeignService.reduct(productId, amount);
        System.out.println(s);
//         返回订单
        System.out.println("提交订单成功");
        return "提交订单成功";
    }

}
