package ra.com.modules.orders.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.com.modules.orders.Order;
import ra.com.modules.products.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {

    private Integer id;
    private String createdDate;
    private String address;
    private String paymentMethod;
    private String orderStatus;
    private Integer userId;
    private boolean isCart;


    public OrderResponse(Order order) {

        this.id = order.getId();
        this.createdDate = order.getCreatedDate().toString();
        this.address = order.getAddress();
        this.paymentMethod = order.getPaymentMethod();
        this.orderStatus = order.getOrderStatus().toString();
        this.userId = order.getUser().getId();
        this.isCart = order.isCart();

    }
}
