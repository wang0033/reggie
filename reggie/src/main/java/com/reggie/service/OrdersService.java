package com.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.domain.Orders;

/**
 * @author 86182
 * @create 2022/9/4 20:47
 */
public interface OrdersService extends IService<Orders> {
    void submit(Orders orders);
}
