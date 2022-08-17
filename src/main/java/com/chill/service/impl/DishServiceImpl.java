package com.chill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chill.common.Result;
import com.chill.dto.DishDTO;
import com.chill.entity.Category;
import com.chill.entity.Dish;
import com.chill.entity.DishFlavor;
import com.chill.mapper.DishFlavorMapper;
import com.chill.mapper.DishMapper;
import com.chill.service.CategoryService;
import com.chill.service.DishFlavorService;
import com.chill.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    @Transactional
    public Result saveDish(DishDTO dishDTO) {
        saveOrUpdate(dishDTO);
        Long id = dishDTO.getId();
        QueryWrapper<DishFlavor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id",id);
        dishFlavorService.remove(queryWrapper);
        List<DishFlavor> dishFlavors = dishDTO.getFlavors();
        dishFlavors.stream().map((d)->{d.setDishId(id);return d;}).collect(Collectors.toList());
        dishFlavorService.saveOrUpdateBatch(dishFlavors);
        return Result.success("success");
    }

    @Override
    public Result pageDishDTO(IPage<DishDTO> page, String name) {
        IPage data = dishMapper.pageDishDTO(page,name);
        return Result.success(data);
    }

    @Override
    public Result queryByDishId(Long id) {
        Dish dish = getById(id);
        DishDTO dishDTO = BeanUtil.copyProperties(dish, DishDTO.class);
        Long categoryId = dish.getCategoryId();
//        dishDTO.setCategoryName(categoryService.query().select("name").eq("id",categoryId).one().getName());
        List<DishFlavor> dishFlavors = dishFlavorService.query().eq("dish_id",id).list();
        dishDTO.setFlavors(dishFlavors);
        return Result.success(dishDTO);
    }

    @Override
    public Result updateDishStatus(List<Long> ids) {
        List<Dish> dishes = new ArrayList<>(ids.size());
        for (int i=0;i < ids.size();i++){
            Dish dish = new Dish();
            dish.setId(ids.get(i));
            dish.setStatus(0);
            dishes.add(dish);
        }
        updateBatchById(dishes);
        return Result.success("success");
    }

    @Override
    public Result updateDishStatusSale(List<Long> ids) {
        List<Dish> dishes = new ArrayList<>(ids.size());
        for (int i=0;i < ids.size();i++){
            Dish dish = new Dish();
            dish.setId(ids.get(i));
            dish.setStatus(1);
            dishes.add(dish);
        }
        updateBatchById(dishes);
        return Result.success("success");
    }

    @Override
    @Transactional
    public Result deleteDish(List<Long> ids) {
        for (Long id : ids){
            dishFlavorMapper.delByDishId(id);
        }
        removeBatchByIds(ids);
        return Result.success("删除成功");
    }

    @Override
    public Result queryByCategoryId(Long categoryId,String name) {

        List<Dish> dishes = query().eq(categoryId !=null,"category_id", categoryId).like(StrUtil.isNotBlank(name),"name",name).eq("status",1).list();
        List<DishDTO> dishDtoList = dishes.stream().map((item) -> {
            DishDTO dishDto = new DishDTO();

            BeanUtils.copyProperties(item,dishDto);

            Long categoryID = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryService.getById(categoryID);

            if(category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            //当前菜品的id
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId,dishId);
            //SQL:select * from dish_flavor where dish_id = ?
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
            dishDto.setFlavors(dishFlavorList);
            return dishDto;
        }).collect(Collectors.toList());
        return Result.success(dishDtoList);
    }


}
