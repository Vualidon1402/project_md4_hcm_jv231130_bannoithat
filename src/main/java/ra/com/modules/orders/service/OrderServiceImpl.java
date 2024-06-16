package ra.com.modules.orders.service;

import org.springframework.stereotype.Service;
import ra.com.modules.orderDetail.OrderDetail;
import ra.com.modules.orders.Order;
import ra.com.modules.orders.dao.IOrderDao;
import ra.com.modules.products.Product;
import ra.com.modules.products.dao.IProductDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    private final IOrderDao orderDao;
    private final IProductDao productDao;

    public OrderServiceImpl(IOrderDao orderDao, IProductDao productDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
    }


    @Override
    public void addProductToOrder(Integer productId, Order order) {
        // Fetch the product from the database
        Product product = productDao.findById(productId);

        // Create a new OrderDetail
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setPrice(product.getPrice());
        orderDetail.setQuantity(1); // Set quantity to 1 or any other value based on your requirements

        // Add the OrderDetail to the order
        order.getOrderDetails().add(orderDetail);

        // Save the order
        orderDao.save(order);
    }

    @Override
    public void checkout(Order order) {
        // Update the order status to CONFIRM or any other status that indicates the order has been checked out
        order.setOrderStatus(Order.OrderStatus.CONFIRM);

        // Save the order
        orderDao.save(order);
    }

    //findAll
    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    //findById
    @Override
    public Order findById(Integer id) {
        return orderDao.findById(id);
    }

    //save
    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    //deleteById
    @Override
    public void deleteById(Integer id) {
        orderDao.deleteById(id);
    }
}
