package ra.com.modules.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.com.modules.orderDetail.OrderDetail;
import ra.com.modules.users.Users;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "address")
    private String address;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "orderStatus")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    private boolean isCart;

    public enum OrderStatus {
        PENDING,
        CONFIRM,
        SHIPPING,
        DELIVERY,
        CANCEL,
        SHOPPING
    }

}