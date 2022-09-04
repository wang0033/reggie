package com.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.domain.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86182
 * @create 2022/9/4 20:48
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
