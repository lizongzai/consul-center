package com.example.controller;


import com.example.pojo.Order;
import com.example.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-26
 */
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private IOrderService orderService;

  @GetMapping("/{id}")
  public Order getOrderByIdd(@PathVariable("id") Integer id) {

    return orderService.getOrderById(id);
  }

}
