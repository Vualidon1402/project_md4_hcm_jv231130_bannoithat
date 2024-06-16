package ra.com.modules.orders.service;

import ra.com.modules.orders.Order;

import java.util.List;

public interface IOrderService {
    void addProductToOrder(Integer productId, Order order);

    void checkout(Order order);

    List<Order> findAll();

    Order findById(Integer id);

    void save(Order order);

    void deleteById(Integer id);

}
