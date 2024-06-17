package ra.com.modules.orders.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private Integer id;
    private String createdDate;
    private String address;
    private String paymentMethod;
    private String orderStatus;
    private Integer userId;
    private boolean isCart;

}
