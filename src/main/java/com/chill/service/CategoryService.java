package com.chill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.common.Result;
import com.chill.entity.Category;

public interface CategoryService extends IService<Category> {
    Result saveCateGory(Category category);

    Result queryPage(int pageNum, int pageSize);

    Result removeCategory(Long ids);

    Result updateByCategory(Category category);
}
