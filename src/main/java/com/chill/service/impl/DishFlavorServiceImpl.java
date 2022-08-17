package com.chill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chill.entity.DishFlavor;
import com.chill.mapper.DishFlavorMapper;
import com.chill.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
