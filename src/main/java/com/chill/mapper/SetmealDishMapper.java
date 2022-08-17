package com.chill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chill.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {
    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    int delSetmealDishBySetmealId(@Param("id") Long id);
}
