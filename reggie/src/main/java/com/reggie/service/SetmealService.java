package com.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.domain.Setmeal;
import com.reggie.dto.SetmealDto;

import java.util.List;

/**
 * @author 86182
 * @create 2022/8/21 15:54
 */
public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
    void removeWithDish(List<Long> ids);
}
