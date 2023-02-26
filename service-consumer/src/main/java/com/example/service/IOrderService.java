package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Order;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-26
 */
public interface IOrderService extends IService<Order> {

  /**
   * 功能描述: 根据主键查询订单
   *
   * @param id
   * @return
   */
  Order getOrderById(Integer id);
}
