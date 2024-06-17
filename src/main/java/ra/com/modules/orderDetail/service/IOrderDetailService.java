package ra.com.modules.orderDetail.service;

import ra.com.modules.orderDetail.OrderDetail;
import ra.com.modules.orderDetail.dto.response.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetailResponse> findAll();

    OrderDetail findById(Integer id);



    void deleteById(Integer id);
    double calculateTotalPrice(List<OrderDetailResponse> orderDetails);

}
