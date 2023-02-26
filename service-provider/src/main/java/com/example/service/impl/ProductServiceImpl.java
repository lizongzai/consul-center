package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ProductMapper;
import com.example.pojo.Product;
import com.example.service.IProductService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-25
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements
    IProductService {

  @Autowired
  private ProductMapper productMapper;

  /**
   * 功能描述: 获取商品列表
   *
   * @return
   */
  @Override
  public List<Product> getAllProductList() {

  //    return Arrays.asList(
  //        new Product(1, "华为手机", 1, 5800D),
  //        new Product(2, "连续电脑", 1, 9999D),
  //        new Product(3, "苹果手机", 1, 9899D)
  //    );

    return productMapper.getAllProductList();
  }
}
