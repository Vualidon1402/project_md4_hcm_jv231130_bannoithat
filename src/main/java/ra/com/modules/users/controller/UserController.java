package ra.com.modules.users.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.com.modules.users.Users;
import ra.com.modules.users.dto.request.UsersRequest;
import ra.com.modules.users.service.IUsersService;

import java.sql.Date;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private IUsersService usersService;
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/cart")
    public String booking() {
        return "cart";
    }

    @GetMapping("/checkout")
    public String menu() {
        return "checkout";
    }

    @GetMapping("/services")
    public String service() {
        return "service";
    }

    @GetMapping("/shop")
    public String team() {
        return "shop";
    }

    @GetMapping("/thank-you")
    public String testimonials() {
        return "thankyou";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/user-info")
    public String userInfo() {
        return "user-info";
    }

    @GetMapping("/order-history")
    public String orderHistory() {
        return "order-history";
    }

    @GetMapping("/login-register")
    public String loginForm() {
        return "/login-register/login-register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("register-username") String username,
            @RequestParam("register-email") String email,
            @RequestParam("register-password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("gender") String genderString // Thay đổi kiểu dữ liệu thành String
    ) {
        Users.userGender gender = Users.userGender.valueOf(genderString); // Chuyển đổi chuỗi thành enum

        Users user = Users.builder()
                .userName(username)
                .userEmail(email)
                .userPassword(password)
                .userPhone(phone)
                .userAddress(address)
                .userGender(gender) // Sử dụng đối tượng enum
                .build();

        user.setUserRole(Users.userRole.MEMBER);
        user.setUserStatus(Users.userStatus.ACTIVE);

        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setUserName(user.getUserName());
        usersRequest.setUserPassword(user.getUserPassword());
        usersRequest.setUserRole(user.getUserRole());
        usersRequest.setUserStatus(user.getUserStatus());
        usersRequest.setUserEmail(user.getUserEmail());
        usersRequest.setUserAddress(user.getUserAddress());
        usersRequest.setUserGender(user.getUserGender());
        usersRequest.setUserPhone(user.getUserPhone());
        usersRequest.setCreatedDate(new Date(System.currentTimeMillis()));

        usersService.save(usersRequest);
        return "redirect:/login-register";
    }
}
