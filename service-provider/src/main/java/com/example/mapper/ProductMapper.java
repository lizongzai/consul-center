package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Product;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-25
 */
public interface ProductMapper extends BaseMapper<Product> {

  List<Product> getAllProductList();
}
