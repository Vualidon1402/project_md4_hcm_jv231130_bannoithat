package ra.com.modules.orderDetail.dao;

import ra.com.modules.orderDetail.OrderDetail;

import javax.swing.*;
import java.util.List;

public interface IOrderDetailDao {
    List<OrderDetail> findAll();

    OrderDetail findById(Integer id);

    void save(OrderDetail orderDetail);

    void deleteById(Integer id);

    List<OrderDetail> findByOrderId(Integer orderId);



}
