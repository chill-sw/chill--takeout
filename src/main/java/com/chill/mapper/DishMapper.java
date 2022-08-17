package com.chill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chill.dto.DishDTO;
import com.chill.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    IPage<DishDTO> pageDishDTO(IPage<DishDTO> page,String name);
}
