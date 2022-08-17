package com.chill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.common.Result;
import com.chill.entity.ShoppingCart;


public interface ShoppingCartService extends IService<ShoppingCart> {

    Result addDishInCar(ShoppingCart shoppingCart);

    Result getShoppingCart();

    Result cleanShoppingCart();

    Result subDishInCar(ShoppingCart shoppingCart);
}
