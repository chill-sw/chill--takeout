package com.chill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.chill.dto.SetmealDTO;
import com.chill.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {
    IPage<SetmealDTO> pageSetmealDTO(IPage<SetmealDTO> page,String name);
}
