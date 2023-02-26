package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Order;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-26
 */
public interface OrderMapper extends BaseMapper<Order> {


  Order getOrderById(@Param("id") Integer id);
}
