package com.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86182
 * @create 2022/8/13 17:51
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
