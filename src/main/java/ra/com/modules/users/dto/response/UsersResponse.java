package ra.com.modules.users.dto.response;

import lombok.*;
import ra.com.modules.users.Users;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class UsersResponse {


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


    public UsersResponse(Users users){
        this.id = users.getId();
        this.userName = users.getUserName();
        this.userPassword = users.getUserPassword();
        this.userRole = users.getUserRole();
        this.userPhone = users.getUserPhone();
        this.userAddress = users.getUserAddress();
        this.userEmail = users.getUserEmail();
        this.userGender = users.getUserGender();
        this.userStatus = users.getUserStatus();
        this.createdDate = users.getCreatedDate();
        this.updatedDate = users.getUpdatedDate();
    }

}