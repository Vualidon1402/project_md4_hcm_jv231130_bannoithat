package ra.com.modules.users;


import lombok.*;
import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;

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


    private String userName;


    private String userPassword;


    private userRole userRole;


    private String userPhone;


    private String userAddress;


    private String userEmail;


    private userGender userGender;


    private userStatus userStatus;

    private Date createdDate;
    private Date updatedDate;


}

