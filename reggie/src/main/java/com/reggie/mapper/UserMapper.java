package com.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86182
 * @create 2022/9/3 22:47
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
