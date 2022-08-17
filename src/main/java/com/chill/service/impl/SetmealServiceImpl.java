package com.chill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chill.common.Result;
import com.chill.dto.SetmealDTO;
import com.chill.entity.Dish;
import com.chill.entity.Setmeal;
import com.chill.entity.SetmealDish;
import com.chill.mapper.SetmealDishMapper;
import com.chill.mapper.SetmealMapper;
import com.chill.service.SetmealDishService;
import com.chill.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional
    public Result saveSetmeal(SetmealDTO setmealDTO) {
        save(setmealDTO);
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes().stream().map(s->{
            s.setSetmealId(setmealDTO.getId());
            return s;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
        return Result.success("success");
    }

    @Override
    public Result pageForSetmealDTO(Page<SetmealDTO> page, String name) {
        return Result.success(setmealMapper.pageSetmealDTO(page,name));
    }

    @Override
    public Result updateSetmealStatus(List<Long> ids) {
        List<Setmeal> setmeals = new ArrayList<>(ids.size());
        for (int i=0;i < ids.size();i++){
            Setmeal setmeal = new Setmeal();
            setmeal.setId(ids.get(i));
            setmeal.setStatus(0);
            setmeals.add(setmeal);
        }
        updateBatchById(setmeals);
        return Result.success("success");
    }

    @Override
    public Result updateSetmealStatusSale(List<Long> ids) {
        List<Setmeal> setmeals = new ArrayList<>(ids.size());
        for (int i=0;i < ids.size();i++){
            Setmeal setmeal = new Setmeal();
            setmeal.setId(ids.get(i));
            setmeal.setStatus(1);
            setmeals.add(setmeal);
        }
        updateBatchById(setmeals);
        return Result.success("success");
    }

    @Override
    @Transactional
    public Result deleteSetmeal(List<Long> ids) {
        //先判断套餐是否停售

        for (Long id : ids){
            Setmeal flag = query().eq("id", id).eq("status", 0).one();
            if (flag == null){
                return Result.error("商品正在售卖中,请先将套餐停售");
            }
            setmealDishMapper.delSetmealDishBySetmealId(id);
        }
        removeBatchByIds(ids);
        return Result.success("删除成功");
    }

    @Override
    public Result listSetmeal(Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = list(queryWrapper);
        return Result.success(list);
    }
}
