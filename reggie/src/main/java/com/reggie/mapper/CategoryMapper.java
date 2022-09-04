package com.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.domain.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86182
 * @create 2022/8/20 17:18
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
