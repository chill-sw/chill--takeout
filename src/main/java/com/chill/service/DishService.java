package com.chill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.common.Result;
import com.chill.dto.DishDTO;
import com.chill.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    Result saveDish(DishDTO dishDTO);

    Result pageDishDTO(IPage<DishDTO> page,String name);

    Result queryByDishId(Long id);

    Result updateDishStatus(List<Long> ids);

    Result updateDishStatusSale(List<Long> ids);

    Result deleteDish(List<Long> ids);

    Result queryByCategoryId(Long categoryId,String name);


}
