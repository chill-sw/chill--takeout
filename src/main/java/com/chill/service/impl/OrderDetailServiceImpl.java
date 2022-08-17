package com.chill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chill.entity.OrderDetail;
import com.chill.mapper.OrderDetailMapper;
import com.chill.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}