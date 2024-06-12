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


    private Integer id;

    @NotEmpty
    @Size(max = 50)
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String userPassword;

    @NotNull
    private Users.userRole userRole;

    @Pattern(regexp = "^\\d{10}$")
    private String userPhone;

    @NotEmpty
    private String userAddress;

    @NotEmpty
    @Email
    private String userEmail;

    @NotNull
    private Users.userGender userGender;







    public UsersResponse(Users users){
        this.id = users.getId();
        this.userName = users.getUserName();
        this.userPassword = users.getUserPassword();
        this.userRole = users.getUserRole();
        this.userPhone = users.getUserPhone();
        this.userAddress = users.getUserAddress();
        this.userEmail = users.getUserEmail();
        this.userGender = users.getUserGender();

    }

}