package ra.com.modules.orders.service;

import org.springframework.stereotype.Service;
import ra.com.modules.orderDetail.OrderDetail;
import ra.com.modules.orders.Order;
import ra.com.modules.orders.dao.IOrderDao;
import ra.com.modules.orders.dto.response.OrderResponse;
import ra.com.modules.products.Product;
import ra.com.modules.products.dao.IProductDao;
import ra.com.modules.users.Users;
import ra.com.modules.users.dao.IUsersDao;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    private final IOrderDao orderDao;
    private final IProductDao productDao;

    private final IUsersDao usersDao;

    public OrderServiceImpl(IOrderDao orderDao, IProductDao productDao, IUsersDao usersDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.usersDao = usersDao;
    }


    @Override
    public void checkout(Order order) {
        // Set isCart to false for the Order
        order.setCart(false);
        // Update the order status to CONFIRM or any other status that indicates the order has been checked out
        order.setOrderStatus(Order.OrderStatus.CONFIRM);
        orderDao.save(order);
    }

    //findAll
    @Override
    public List<OrderResponse> findAll() {
        List<Order> list = orderDao.findAll();
        return list.stream().map(OrderResponse::new).collect(Collectors.toList());
    }

    //findById
    @Override
    public Order findById(Integer id) {
        return orderDao.findById(id);
    }

    @Override
    public void save(Integer userId, Integer productId, Integer quantity, boolean isCheckout) {
        // Find the user
        Users user = usersDao.findById(userId);

        // Find the product
        Product product = productDao.findById(productId);

        // Get the user's current order or create a new one if it doesn't exist
        Order order = user.getOrders().isEmpty() ? new Order() : user.getOrders().get(user.getOrders().size() - 1);
        if (order.getId() == null) {
            order.setUser(user);
            order.setCart(true); // Set isCart to true for a new order
            user.getOrders().add(order);
        }

        // Initialize the orderDetails list if it's null
        if (order.getOrderDetails() == null) {
            order.setOrderDetails(new ArrayList<>());
        }

        // Check if the product already exists in the order
        OrderDetail existingOrderDetail = null;
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            if (orderDetail.getProduct().getId().equals(product.getId())) {
                existingOrderDetail = orderDetail;
                break;
            }
        }

        if (existingOrderDetail == null) {
            // If the product is not in the order, add it with the specified quantity
            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setProduct(product);
            newOrderDetail.setQuantity(quantity);
            newOrderDetail.setPrice(product.getPrice());
            newOrderDetail.setOrder(order);
            order.getOrderDetails().add(newOrderDetail);
        } else {
            // If the product is already in the order, increase the quantity by the specified quantity
            existingOrderDetail.setQuantity(existingOrderDetail.getQuantity() + quantity);
        }

        if (isCheckout) {
            // If place order, set isCart to false, display the createdDate and set OrderStatus to PENDING
            order.setCart(false);
            order.setCreatedDate(new Date());
            order.setOrderStatus(Order.OrderStatus.PENDING);
        } else {
            // If not checkout, set OrderStatus to SHIPPING
            order.setOrderStatus(Order.OrderStatus.SHOPPING);
        }

        // Save the order
        orderDao.save(order);
    }
    //save


    //dleteById
    @Override
    public void deleteById(Integer id) {
        orderDao.deleteById(id);
    }


}

