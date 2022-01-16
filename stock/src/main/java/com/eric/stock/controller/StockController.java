package com.eric.stock.controller;

import com.eric.stock.dao.MyStockMapper;
import com.eric.stock.generate.Stock;
import com.eric.stock.generate.StockExample;
import com.eric.stock.generate.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    MyStockMapper myStockMapper;

    @Autowired
    StockMapper stockMapper;

    @RequestMapping("/reduct")
    public String reduct(@RequestParam("productId") Long productId,
                         @RequestParam("amount") Integer amount) {
        Stock stock = myStockMapper.selectByProductId(productId);
        if (stock == null) {
            return "找不到该产品的库存";
        }
        Integer count = stock.getCount();
        if (count < amount) {
            return "库存不足";
        }
        stock.setCount(count - amount);

        StockExample stockExample = new StockExample();
        stockExample.createCriteria().andProductIdEqualTo(productId);
        int updateCount = stockMapper.updateByExampleSelective(stock, stockExample);
        System.out.println("减库存:"+updateCount);
        return updateCount == 1 ? "减库存成功" : "减库存失败";
    }

    @RequestMapping("/test")
    public String test() {
//        System.out.println("name:"+name+",password"+password);
        System.out.println("调用成功");
        return "调用成功";
    }

}
