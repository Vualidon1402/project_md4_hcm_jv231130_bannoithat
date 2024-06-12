package ra.com.modules.users.dto.request;

import lombok.*;
import ra.com.modules.users.Users;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UsersRequest {
    public enum userRole {
        ADMIN,
        USER
    }

    public enum userGender{
        Male,
        Female
    }

    public enum userStatus{
        ACTIVE,
        INACTIVE
    }


    private Integer id;


    private String userName;


    private String userPassword;


    private Users.userRole userRole;


    private String userPhone;


    private String userAddress;


    private String userEmail;


    private Users.userGender userGender;


    private Users.userStatus userStatus;

    private Date createdDate;
    private Date updatedDate;



}