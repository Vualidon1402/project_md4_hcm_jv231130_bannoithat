package ra.com.modules.users.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.com.modules.products.dto.response.ProductResponse;
import ra.com.modules.products.service.IProductService;
import ra.com.modules.users.Users;
import ra.com.modules.users.dto.request.UsersRequest;
import ra.com.modules.users.dto.response.UsersResponse;
import ra.com.modules.users.service.IUsersService;

import javax.validation.Valid;
import javax.validation.ValidatorFactory;
import javax.xml.validation.Validator;
import java.sql.Date;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private IUsersService usersService;

    @Autowired
    private IProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userProduct", productService.findAll());
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
    public String team(Model model) {
        model.addAttribute("userProduct", productService.findAll());
        return "shop";
    }

    @GetMapping("/detail/{id}")
    public String shopDetail(@PathVariable("id") Integer id, Model model) {
        ProductResponse product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product-detail"; // Tên của view (template HTML)
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

    @GetMapping("/register")
    public String loginForm(Model model) {
        model.addAttribute("users", new UsersRequest());
        return "/login-register/register";
    }

    @PostMapping("/registerForm")
    public String register(@Valid @ModelAttribute("users") UsersRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", request);
            return "login-register/register";
        }
        // Log thông báo
        System.out.println("Đã đăng ký thành công!");
        usersService.save(request);
        System.out.println("Đã lưu thông tin người dùng!");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new UsersResponse());
        return "login-register/login";
    }

    @PostMapping("/loginForm")
    public String loginForm(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword, Model model) {
        // Kiểm tra userName và userPassword
        Users user = usersService.findByUserName(userName);
        if (user != null && user.getUserPassword().equals(userPassword)) {
            // Log thông báo
            System.out.println("Đã đăng nhập thành công!");

            // Kiểm tra userRole và chuyển hướng tương ứng
            if (user.getUserRole() == Users.userRole.MEMBER) {
                return "redirect:/";
            } else if (user.getUserRole() == Users.userRole.ADMIN) {
                return "redirect:/admin";
            }
        }

        // Nếu không tìm thấy người dùng hoặc mật khẩu không khớp, quay lại trang đăng nhập
        System.out.println("Đăng nhập thất bại!");
        model.addAttribute("users", new UsersResponse());
        return "login-register/login";
    }



}
