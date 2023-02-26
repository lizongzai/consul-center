package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-26
 */
public interface IOrderService extends IService<Order> {

  Order getOrderById(Integer id);
}
