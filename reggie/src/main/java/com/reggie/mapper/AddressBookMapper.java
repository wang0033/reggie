package com.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.domain.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86182
 * @create 2022/9/4 16:15
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
