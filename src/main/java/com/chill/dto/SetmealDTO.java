package com.chill.dto;

import com.chill.entity.Setmeal;
import com.chill.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDTO extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
