package ra.com.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.com.modules.category.dto.request.CategoryRequest;
import ra.com.modules.category.dto.response.CategoryResponse;
import ra.com.modules.category.service.ICategoryService;
import ra.com.modules.products.dto.request.ProductRequest;
import ra.com.modules.products.dto.response.ProductResponse;
import ra.com.modules.products.service.IProductService;
import ra.com.modules.products.validator.ProductValidator;
import ra.com.modules.users.dto.request.UsersRequest;
import ra.com.modules.users.service.IUsersService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductValidator validator;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IUsersService usersService;

    @GetMapping
    public String admin() {
        return "admin/index"; // tên view
    }

    @GetMapping("/category")
    public String category(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
            Model model) {
        long totalElements = categoryService.getTotalsElement();

        long nguyen = totalElements / limit;
        long du = totalElements % limit;
        long totalPages = du == 0 ? nguyen : nguyen + 1;

        model.addAttribute("categories", categoryService.findByPagination(page, limit));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        model.addAttribute("category", new CategoryRequest());
        return "admin/category/category"; // tên view
    }

    @GetMapping("/receipt")
    public String receipt() {
        return "admin/receipt";
    }

    @GetMapping("/product")  // prooduct list
    public String product(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "3") Integer limit, Model model) {
        // phân trang
        // tổng số trang
        // sô luong sp trên trang
        // trang hiện tại (tính từ 0)
        long totalElements = productService.getTotalsElement();

        long nguyen = totalElements / limit;
        long du = totalElements % limit;
        long totalPages = du == 0 ? nguyen : nguyen + 1;

        model.addAttribute("products", productService.findByPagination(page, limit));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        model.addAttribute("product", new ProductRequest());
        return "admin/product/product"; // tên view
    }

    @GetMapping("user")
    public String user(Model model){
        model.addAttribute("adminUser",usersService.findAll());
        return "admin/user";
    }

    // product mananger
    @GetMapping("/product/add")
    public String add(Model model) {
        model.addAttribute("product", new ProductRequest());
        return "admin/product/product-add";
    }

    @PostMapping("/product/add")
    public String doAdd(@Valid @ModelAttribute("product") ProductRequest request, BindingResult result, Model model) {
//         validator.validate(request,result);
        if (result.hasErrors()) {
            model.addAttribute("product", request);
            return "admin/product/product-add";
        }
        productService.save(request);
        return "redirect:/admin/product"; //điều hướng theo đường dẫn
    }

    @PostMapping("/product/update")
    public String doUpdate(@ModelAttribute("product") ProductRequest request) {
        productService.save(request);
        return "redirect:/admin/product"; //điều hướng theo đường dẫn
    }

    @GetMapping("/product/delete")
    public String doDelete(@RequestParam("id") Integer idDel) {
        productService.deleteById(idDel);
        return "redirect:/admin/product"; //điều hướng theo đường dẫn
    }

    @GetMapping("/product/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("products", productService.searchByName(keyword));
        model.addAttribute("keyword", keyword);
        model.addAttribute("product", new ProductRequest());
        return "admin/product/product"; // tên view
    }

    @GetMapping("/product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        ProductResponse productResponse = productService.findById(id);
        model.addAttribute("product", productResponse);
        return "admin/product/product-edit";
    }

    //Category manager
    @GetMapping("/category/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new CategoryRequest());
        return "admin/category/category-add";
    }

    @PostMapping("/category/add")
    public String doAddCategory(@Valid @ModelAttribute("category") CategoryRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", request);
            return "admin/category/category-add";
        }
        categoryService.save(request);
        return "redirect:/admin/category"; //điều hướng theo đường dẫn
    }

    @PostMapping("/category/update")
    public String doUpdateCategory(@ModelAttribute("category") CategoryRequest request) {
        categoryService.save(request);
        return "redirect:/admin/category"; //điều hướng theo đường dẫn
    }

    @GetMapping("/category/delete")
    public String doDeleteCategory(@RequestParam("id") Integer idDel) {
        categoryService.deleteById(idDel);
        return "redirect:/admin/category"; //điều hướng theo đường dẫn
    }

    @GetMapping("/category/search")
    public String searchCategory(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("categories", categoryService.searchByName(keyword));
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", new CategoryRequest());
        return "admin/category/category"; // tên view
    }

    @GetMapping("/category/edit/{id}")
    public String editCategory(@PathVariable Integer id, Model model) {
        CategoryResponse categoryResponse = categoryService.findById(id);
        model.addAttribute("category", categoryResponse);
        return "admin/category/category-edit";
    }

}
