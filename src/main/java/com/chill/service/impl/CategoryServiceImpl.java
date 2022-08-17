package com.chill.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chill.common.CustomException;
import com.chill.common.Result;
import com.chill.entity.Category;
import com.chill.entity.Dish;
import com.chill.mapper.CategoryMapper;
import com.chill.service.CategoryService;
import com.chill.service.DishService;
import com.chill.service.EmployeeService;
import com.chill.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public Result saveCateGory(Category category) {
        save(category);
        return Result.success("success");
    }

    @Override
    public Result queryPage(int pageNum, int pageSize) {
        Page<Category> page = query().orderByAsc("sort").page(new Page<>(pageNum,pageSize));
        return Result.success(page);
    }

    @Override
    public Result removeCategory(Long ids) {
        //先查关联菜品是否存在,存在返回,不给删除
        Long countForDish = dishService.query().eq("category_id", ids).count();
        if (countForDish == null){
            throw new CustomException("分类不存在");
        }
        if (countForDish > 0){
            throw new CustomException("还有关联菜品,不能删除");
            //return Result.error("还有关联菜品,不能删除");
        }
        //查当前分类是否关联套餐关联套餐,存在返回,不给删除
        Long countForSetmeal = setmealService.query().eq("category_id", ids).count();
        if (countForSetmeal == null){
            throw new CustomException("分类不存在");
        }
        if (countForSetmeal > 0){
            return Result.error("还有关联套餐,不能删除");
        }

        removeById(ids);
        return Result.success("删除成功");
    }

    @Override
    public Result updateByCategory(Category category) {
        updateById(category);
        return Result.success("修改成功!");
    }
}
