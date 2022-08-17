package com.chill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.common.Result;
import com.chill.entity.Orders;


public interface OrderService extends IService<Orders> {

    Result submitOrder(Orders orders);
}
