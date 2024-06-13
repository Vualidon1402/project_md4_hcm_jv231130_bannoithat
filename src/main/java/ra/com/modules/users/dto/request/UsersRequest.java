package ra.com.modules.users.dto.request;

import lombok.*;
import ra.com.modules.users.Users;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;
import javax.persistence.Temporal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UsersRequest {


    public enum userGender {
        Male,
        Female
    }


    public enum userStatus {
        ACTIVE,
        INACTIVE
    }



    private Integer id;

    @NotEmpty
    @Size(max = 50)
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String userPassword;



    @Pattern(regexp = "^\\d{10}$")
    private String userPhone;

    @NotEmpty
    private String userAddress;

    @NotEmpty
    @Email
    private String userEmail;

    @NotNull
    private Users.userGender userGender;








}