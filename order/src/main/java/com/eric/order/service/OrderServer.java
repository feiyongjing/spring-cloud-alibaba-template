package com.eric.order.service;

import com.eric.order.generate.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServer {

    @Autowired
    OrderMapper orderMapper;


}
