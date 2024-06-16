package ra.com.modules.orderDetail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.com.modules.orderDetail.OrderDetail;
import ra.com.modules.orderDetail.dao.IOrderDetailDao;
import ra.com.modules.orderDetail.dto.response.OrderDetailResponse;
import ra.com.modules.products.dao.IProductDao;
import ra.com.modules.users.dao.IUsersDao;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private IOrderDetailDao orderDetailDao;
    @Autowired
    private IUsersDao usersDao;
    @Autowired
    private IProductDao productDao;

    @Override
    public List<OrderDetailResponse> findAll() {
       List<OrderDetail> list = orderDetailDao.findAll();
         return list.stream().map(OrderDetailResponse ::new).collect(Collectors.toList());
    }

    @Override
    public OrderDetail findById(Integer id) {
        return orderDetailDao.findById(id);
    }

    @Override
    public double calculateTotalPrice(List<OrderDetailResponse> orderDetails) {
        double totalPrice = 0.0;
        for (OrderDetailResponse orderDetail : orderDetails) {
            totalPrice += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public void deleteById(Integer id) {
        orderDetailDao.deleteById(id);
    }
}
