package com.chill.controller;

import com.chill.common.Result;
import com.chill.entity.Category;
import com.chill.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品或者套餐
     * @param category
     * @return
     */
    @PostMapping
    public Result saveCategory(@RequestBody Category category){
        return categoryService.saveCateGory(category);
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam(value = "page") int pageNum,
            @RequestParam int pageSize
    ){
        return categoryService.queryPage(pageNum,pageSize);
    }

    @DeleteMapping()
    public Result deleteCategory(Long ids){
        return categoryService.removeCategory(ids);
    }

    @PutMapping
    public Result update(@RequestBody Category category){
        return categoryService.updateByCategory(category);
    }

    @GetMapping("/list")
    public Result queryByCategoryType(@RequestParam(defaultValue = "") Integer type){
        List<Category> categories = categoryService.query().eq(type != null,"type", type).list();
        return Result.success(categories);
    }

}
