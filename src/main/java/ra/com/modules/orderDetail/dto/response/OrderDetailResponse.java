package ra.com.modules.orderDetail.dto.response;

import lombok.*;
import ra.com.modules.orderDetail.OrderDetail;
import ra.com.modules.orders.Order;
import ra.com.modules.products.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailResponse {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double price;
    private Double total;
    private Product product;
    private Order order;



    public OrderDetailResponse(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.orderId = orderDetail.getOrder().getId();
        this.productId = orderDetail.getProduct().getId();
        this.quantity = orderDetail.getQuantity();
        this.price = orderDetail.getPrice();
        this.total = orderDetail.getPrice() * orderDetail.getQuantity();
        this.product = orderDetail.getProduct(); // assuming that Order has a getProduct() method
        this.order = orderDetail.getOrder(); // assuming that OrderDetail has a getOrder() method
    }
}
