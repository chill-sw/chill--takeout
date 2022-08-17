package com.chill.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chill.common.Result;
import com.chill.dto.SetmealDTO;
import com.chill.entity.Setmeal;
import com.chill.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /*
    * 添加套餐
    * */
    @PostMapping
    public Result saveSetmeal(@RequestBody SetmealDTO setmealDTO){
        return setmealService.saveSetmeal(setmealDTO);
    }

    @GetMapping("/page")
    public Result pageForSetmealDTO(
            @RequestParam(value = "page") int pageNum,
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "") String name
    ){
        return setmealService.pageForSetmealDTO(new Page<SetmealDTO>(pageNum,pageSize) ,name);
    }

    @PostMapping("/status/0")
    public Result updateSetmealStatus(@RequestParam List<Long> ids){
        return setmealService.updateSetmealStatus(ids);
    }
    /*
     * 启售
     * */
    @PostMapping("/status/1")
    public Result updateSetmealStatusSale(@RequestParam List<Long> ids){
        return setmealService.updateSetmealStatusSale(ids);
    }

    /*
    * 删除套餐or批量删除
    * */
    @DeleteMapping()
    public Result deleteSetmeal(@RequestParam List<Long> ids){
        return setmealService.deleteSetmeal(ids);
    }

    /**
     * 根据条件查询套餐数据
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public Result listSetmeal(Setmeal setmeal){

        return setmealService.listSetmeal(setmeal);
    }
}
