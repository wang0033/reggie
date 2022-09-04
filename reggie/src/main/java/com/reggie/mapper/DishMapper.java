package com.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.domain.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86182
 * @create 2022/8/21 15:48
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
