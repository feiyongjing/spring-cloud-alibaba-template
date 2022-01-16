package com.eric.stock.dao;

import com.eric.stock.generate.Stock;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MyStockMapper {

    @Select("select * from `stock_tbl` where product_id = #{productId}")
    Stock selectByProductId(@Param("productId") Long productId);
}
