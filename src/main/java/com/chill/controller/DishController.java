package com.chill.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chill.common.Result;
import com.chill.dto.DishDTO;
import com.chill.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    /*
    * 新增
    * */
    @PostMapping
    public Result saveDish(@RequestBody DishDTO dishDTO){
        return dishService.saveDish(dishDTO);
    }

    /*
    * 更新
    * */
    @PutMapping
    public Result updateDish(@RequestBody DishDTO dishDTO){
        return dishService.saveDish(dishDTO);
    }

    @GetMapping("/page")
    public Result pageDishDTO(
            @RequestParam(value = "page") int pageNum,
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "") String name
    ){
        return dishService.pageDishDTO(new Page<>(pageNum,pageSize),name);
    }

    @GetMapping("/{id}")
    public Result queryByDish(@PathVariable Long id){
        return dishService.queryByDishId(id);
    }

    @PostMapping("/status/0")
    public Result updateDishStatus(@RequestParam List<Long> ids){
        return dishService.updateDishStatus(ids);
    }
    /*
    * 启售
    * */
    @PostMapping("/status/1")
    public Result updateDishStatusSale(@RequestParam List<Long> ids){
        return dishService.updateDishStatusSale(ids);
    }
    /*
    * 删除or批量删除
    * */
    @DeleteMapping()
    public Result deleteDish(@RequestParam List<Long> ids){
        return dishService.deleteDish(ids);
    }

    /*
     * 添加套餐里的请求菜品
     * */
    @GetMapping("/list")
    public Result queryByCategoryId(@RequestParam(defaultValue = "") Long categoryId,@RequestParam(defaultValue = "") String name){
        return dishService.queryByCategoryId(categoryId,name);
    }

}
