package com.example.controller;


import com.example.pojo.Product;
import com.example.service.IProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private IProductService productService;

  /**
   * 功能描述: 获取商品列表
   *
   * @return
   */
  @GetMapping("/list")
  public List<Product> getAllProductList() {
    return productService.getAllProductList();
  }

}
