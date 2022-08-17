package com.chill.dto;


import com.chill.entity.Dish;
import com.chill.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO extends Dish{
    private List<DishFlavor>  flavors = new ArrayList<>();

    private String categoryName;

//    private Integer copies;
}
