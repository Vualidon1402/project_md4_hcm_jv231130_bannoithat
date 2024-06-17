package ra.com.modules.orders.service;

import ra.com.modules.orderDetail.OrderDetail;
import ra.com.modules.orders.Order;
import ra.com.modules.orders.dto.response.OrderResponse;
import ra.com.modules.products.Product;

import java.util.List;

public interface IOrderService {

    void checkout(Order order);

    List<OrderResponse> findAll();

    Order findById(Integer id);



    //save
    void save(Integer userId, Integer productId, Integer quantity, boolean isCheckout);


    void deleteById(Integer id);



}
