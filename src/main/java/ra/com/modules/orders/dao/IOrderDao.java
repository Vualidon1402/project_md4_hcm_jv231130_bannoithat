package ra.com.modules.orders.dao;

import ra.com.modules.orders.Order;

import java.util.List;


public interface IOrderDao {
    List<Order> findAll();

    Order findById(Integer id);

    void save(Order order);

    void update(Order order);

    void deleteById(Integer id);


}

