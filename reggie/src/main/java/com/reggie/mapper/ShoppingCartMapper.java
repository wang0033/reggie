package com.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.domain.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86182
 * @create 2022/9/4 17:46
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
