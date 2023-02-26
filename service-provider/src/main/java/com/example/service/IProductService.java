package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Product;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-25
 */
public interface IProductService extends IService<Product> {

  List<Product> getAllProductList();
}
