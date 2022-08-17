package com.chill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chill.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
    @Delete("delete from dish_flavor where dish_id = #{id}")
    int delByDishId(@Param("id") Long id);
}
