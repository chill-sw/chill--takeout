package com.chill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chill.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
