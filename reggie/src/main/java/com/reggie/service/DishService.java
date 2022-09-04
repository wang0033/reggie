package com.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.domain.Dish;
import com.reggie.dto.DishDto;

/**
 * @author 86182
 * @create 2022/8/21 15:48
 */
public interface DishService extends IService<Dish> {
    void updateWithFlavor(DishDto dishDto);
    void saveWithFlavor(DishDto dishDto);
    DishDto getByIdWithFlavor(Long id);
}
