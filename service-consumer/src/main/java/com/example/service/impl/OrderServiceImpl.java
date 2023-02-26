package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.OrderMapper;
import com.example.pojo.Order;
import com.example.pojo.Product;
import com.example.service.IOrderService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
//
///**
// * <p>
// * 服务实现类
// * </p>
// *
// * @author lizongzai
// * @since 2023-02-26
// */
//@Service
//public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
//
//  @Autowired
//  private RestTemplate restTemplate;
//  @Autowired
//  private OrderMapper orderMapper;
//
//  @Autowired
//  @Resource
//  private LoadBalancerClient loadBalancerClient; //Ribbon负载均衡器
//
//  @Override
//  public Order getOrderById(Integer id) {
//    Order orderById = orderMapper.getOrderById(id);
//    System.out.println("订单信息 = " + orderById);
//
//    //LoadBalancerAnnotation负载均衡注解调用微服务
////    List<Product> productList = selectProductListByLoadBalancerClient();
//
//    //LoadBalancerAnnotation负载均衡注解调用微服务
//    List<Product> productList = selectProductListByLoadBalancerAnnotation();
//
//    //获取订单信息
//
//    Order order = new Order();
//    order.setId(orderById.getId());
//    order.setOrderNo(orderById.getOrderNo());
//    order.setOrderAddress(orderById.getOrderAddress());
//    order.setTotalPrice(orderById.getTotalPrice());
//    order.setProductList(productList);
//    return order;
//  }
//
//
//  /**
//   * 通过LoadBalancerClient负载均衡器
//   *
//   * @return
//   */
//  private List<Product> selectProductListByLoadBalancerClient() {
//    StringBuffer sb = null;
//
//    //根据服务名称获取微服务
//    ServiceInstance serviceInstance = loadBalancerClient.choose("consul-provider-product");
//    if (serviceInstance == null) {
//      return null;
//    }
//
//    sb = new StringBuffer();
//    sb.append("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/list");
////    sb.append("http://" + serviceInstance.getServiceId().toString() + "/product/list");
//    System.out.println("负载均衡 = " + sb.toString());
//
//    //ResponseEntity封装返回数据
//    ResponseEntity<List<Product>> response = restTemplate.exchange(
//        sb.toString(),
//        HttpMethod.GET,
//        null,
//        new ParameterizedTypeReference<List<Product>>() {
//        });
//    return response.getBody();
//  }
//
//
//  /**
//   * 通过LoadBalancerAnnotation负载均衡注解调用微服务
//   *
//   * @return
//   */
//  private List<Product> selectProductListByLoadBalancerAnnotation() {
//    //ResponseEntity封装返回数据
//    ResponseEntity<List<Product>> response = restTemplate.exchange(
//        "http://consul-provider-product/product/list",
//        HttpMethod.GET,
//        null,
//        new ParameterizedTypeReference<List<Product>>() {
//        });
//    return response.getBody();
//  }
//}


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lizongzai
 * @since 2023-02-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private OrderMapper orderMapper;
  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private LoadBalancerClient loadBalancerClient; //Ribbon负载均衡器


  /**
   * 获取订单
   *
   * @param id
   * @return
   */
  @Override
  public Order getOrderById(Integer id) {

    //DiscoveryClient调用微服务
    //List<Product> productList = selectProductListByDiscoverClient();

    //LoadBalancerClient负载均衡调用微服务
//    List<Product> productList = selectProductListByLoadBalancerClient();

    //LoadBalancerAnnotation负载均衡注解调用微服务
    List<Product> productList = selectProductListByLoadBalancerClient();

    //获取订单信息
    Order orderById = orderMapper.getOrderById(id);
    Order order = new Order();
    order.setId(orderById.getId());
    order.setOrderNo(orderById.getOrderNo());
    order.setOrderAddress(orderById.getOrderAddress());
    order.setTotalPrice(orderById.getTotalPrice());
    order.setProductList(productList);
    return order;
  }

  /**
   * 使用DiscoveryClient调用微服务
   *
   * @return
   */
  private List<Product> selectProductListByDiscoverClient() {

    StringBuffer sb = null;

    //获取服务列表
    List<String> serviceIds = discoveryClient.getServices();
    if (CollectionUtils.isEmpty(serviceIds)) {
      return null;
    }

    //根据服务名称获取微服务
    List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
    if (CollectionUtils.isEmpty(instances)) {
      return null;
    }

    //获取商品列表
    ServiceInstance serviceInstance = instances.get(0);
    sb = new StringBuffer();
    sb.append("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/list");

    //ResponseEntity封装返回数据
    ResponseEntity<List<Product>> response = restTemplate.exchange(
        sb.toString(),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Product>>() {
        });

    return response.getBody();
  }


  /**
   * 通过LoadBalancerClient负载均衡器
   *
   * @return
   */
  private List<Product> selectProductListByLoadBalancerClient() {
    StringBuffer sb = null;

    //根据服务名称获取微服务
    ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
    if (serviceInstance ==null) {
      return null;
    }

    sb = new StringBuffer();
    sb.append("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/list");
    System.out.println("负载均衡 = " + sb.toString());

    //ResponseEntity封装返回数据
    ResponseEntity<List<Product>> response = restTemplate.exchange(
        sb.toString(),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Product>>() {
        });
    return response.getBody();
  }

  /**
   * 通过LoadBalancerAnnotation负载均衡注解调用微服务
   *
   * @return
   */
  private List<Product> selectProductListByLoadBalancerAnnotation() {
    //ResponseEntity封装返回数据
    ResponseEntity<List<Product>> response = restTemplate.exchange(
        "http://service-provider/product/list",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Product>>() {
        });
    return response.getBody();
  }

}