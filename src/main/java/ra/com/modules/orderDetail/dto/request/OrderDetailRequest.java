package ra.com.modules.orderDetail.dto.request;

public class OrderDetailRequest {

    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double price;
    private Double total;

    public OrderDetailRequest() {
    }

    public OrderDetailRequest(Integer orderId, Integer productId, Integer quantity, Double price, Double total) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTotal() {
        return total;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
