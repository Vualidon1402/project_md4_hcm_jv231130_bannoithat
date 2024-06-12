package ra.com.modules.users;


import lombok.*;
import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder

public class Users {
    public enum userRole {
        ADMIN,
        MEMBER
    }

    public enum userGender{
        Male,
        Female
    }

    public enum userStatus{
        ACTIVE,
        INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 50)
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String userPassword;

    @NotNull
    private userRole userRole;

    @Pattern(regexp = "^\\d{10}$")
    private String userPhone;

    @NotEmpty
    private String userAddress;

    @NotEmpty
    @Email
    private String userEmail;

    @NotNull
    private userGender userGender;

    @NotNull
    private userStatus userStatus;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

}

