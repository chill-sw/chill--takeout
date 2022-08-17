package com.chill.controller;

import com.chill.common.Result;
import com.chill.entity.ShoppingCart;
import com.chill.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("list")
    public Result getShoppingCart(){
        return shoppingCartService.getShoppingCart();
    }

    @PostMapping("add")
    public Result addDishInCar(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.addDishInCar(shoppingCart);
    }

    /**
     * 减少菜品
     */
    @PostMapping("sub")
    public Result subDishInCar(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.subDishInCar(shoppingCart);
    }


    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public Result cleanShoppingCart(){
        return shoppingCartService.cleanShoppingCart();
    }
}
