package com.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.domain.Category;

/**
 * @author 86182
 * @create 2022/8/20 17:19
 */
public interface CategoryService extends IService<Category> {
    void currentRemoveById(Long id);
}
