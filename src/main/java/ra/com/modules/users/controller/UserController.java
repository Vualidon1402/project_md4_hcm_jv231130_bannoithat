package ra.com.modules.users.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.com.modules.category.service.ICategoryService;
import ra.com.modules.orders.Order;
import ra.com.modules.orders.service.IOrderService;
import ra.com.modules.products.dto.response.ProductResponse;
import ra.com.modules.products.service.IProductService;
import ra.com.modules.users.Users;
import ra.com.modules.users.dto.request.UsersRequest;
import ra.com.modules.users.dto.response.UsersResponse;
import ra.com.modules.users.service.IUsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private IUsersService usersService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userProduct", productService.findAll());
        model.addAttribute("category", categoryService.findAll());
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

    @GetMapping("/shop/category/{id}")
    public String shopCategory(@PathVariable("id") Integer id, Model model) {
        List<ProductResponse> products = productService.findByCategory(id);
        model.addAttribute("userProByCate", products);
        return "shop-detail";
    }
    @GetMapping("/detail/{id}")
    public String shopDetail(@PathVariable("id") Integer id, Model model) {
        ProductResponse product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("category", productService.findByCategory(product.getCategory().getId()));
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

    //Authen
    @GetMapping("/register")
    public String loginForm(Model model) {
        model.addAttribute("userRegister", new UsersRequest());
        return "/login-register/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegister") UsersRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userRegister", request);
            return "login-register/register";
        }
        if (usersService.existsByUserName(request.getUserName())) {
            result.rejectValue("userName", "error.userName", "Tên tài khoản đã tồn tại");
            return "login-register/register";
        }
        usersService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new UsersResponse());
        return "/login-register/login";
    }

    @PostMapping("/login")
public String doLogin(@ModelAttribute("loginForm") UsersResponse response, HttpSession session, Model model) {
    Users user = usersService.findByUserName(response.getUserName());
    if (user == null || !user.getUserPassword().equals(response.getUserPassword())) {
        model.addAttribute("error", "Tên tài khoản hoặc mật khẩu không đúng");
        return "/login-register/login";
    }
    session.setAttribute("user", user);
    if (user.getUserRole()) {
        return "redirect:/admin";
    } else {
        return "redirect:/";
    }
}

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    //Order
    @PostMapping("/cart/add")
    public String addProductToOrder(@RequestParam Integer productId, @RequestParam Integer quantity, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = new Order();
            session.setAttribute("order", order);
        }
        orderService.addProductToOrder(productId, order);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            model.addAttribute("order", order);
        }
        return "/cart";
    }

}
