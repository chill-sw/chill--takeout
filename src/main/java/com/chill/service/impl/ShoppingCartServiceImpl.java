package com.chill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chill.common.Result;
import com.chill.entity.ShoppingCart;
import com.chill.mapper.ShoppingCartMapper;
import com.chill.service.ShoppingCartService;
import com.chill.util.BaseContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Override
    public Result addDishInCar(ShoppingCart shoppingCart) {

        //设置用户id，指定当前是哪个用户的购物车数据
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);

        Long dishId = shoppingCart.getDishId();


        //查询当前菜品或者套餐是否在购物车中
        //SQL:select * from shopping_cart where user_id = ? and dish_id/setmeal_id = ?
//        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        ShoppingCart cart = query().eq("user_id", currentId)
                .eq(dishId != null, "dish_id", dishId)
                .eq(shoppingCart.getSetmealId() != null, "setmeal_id", shoppingCart.getSetmealId())
                .one();
        if(cart != null){
            //如果已经存在，就在原来数量基础上加一
            Integer number = cart.getNumber();
            cart.setNumber(number + 1);
            updateById(cart);
        }else{
            //如果不存在，则添加到购物车，数量默认就是一
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            save(shoppingCart);
            cart = shoppingCart;
        }

        return Result.success(cart);
    }

    @Override
    public Result subDishInCar(ShoppingCart shoppingCart) {
        Long currentId = BaseContext.getCurrentId();
        Long dishId = shoppingCart.getDishId();
        ShoppingCart one = query().eq("user_id", currentId).eq("dish_id", dishId).one();
        if (one.getNumber() > 1) {
            update().setSql("number = number - 1")
                    .eq("user_id", currentId)
                    .eq("dish_id", dishId).update();
        }
        else {
            QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", currentId);
            queryWrapper.eq("dish_id",dishId);
            remove(queryWrapper);
        }
        return Result.success("success");
    }

    @Override
    public Result getShoppingCart() {

        List<ShoppingCart> list = query().eq("user_id",BaseContext.getCurrentId()).orderByAsc("create_time").list();

        return Result.success(list);
    }

    @Override
    public Result cleanShoppingCart() {
        //SQL:delete from shopping_cart where user_id = ?
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",BaseContext.getCurrentId());
        remove(queryWrapper);
        return Result.success("success");
    }
}
