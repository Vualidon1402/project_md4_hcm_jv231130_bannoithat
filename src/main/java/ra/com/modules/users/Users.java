package ra.com.modules.users;


import lombok.*;
import ra.com.modules.orders.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "users")

public class Users {

    private Boolean userRole = false;

    public enum userGender{
        Male,
        Female
    }

    public enum userStatus{
        ACTIVE,
        INACTIVE
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    private userGender userGender;

    @NotNull
    @Enumerated(EnumType.STRING)
    private userStatus userStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 50)
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String userPassword;

    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải gồm 10 số")
    private String userPhone;

    @NotEmpty
    private String userAddress;

    @NotEmpty
    @Email
    private String userEmail;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private List<Order> orders = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Order> orders = new ArrayList<>();

}

