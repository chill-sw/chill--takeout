package com.chill.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.common.Result;
import com.chill.dto.SetmealDTO;
import com.chill.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    Result saveSetmeal(SetmealDTO setmealDTO);

    Result pageForSetmealDTO(Page<SetmealDTO> page, String name);

    Result deleteSetmeal(List<Long> ids);

    Result updateSetmealStatus(List<Long> ids);

    Result updateSetmealStatusSale(List<Long> ids);

    Result listSetmeal(Setmeal setmeal);

}
