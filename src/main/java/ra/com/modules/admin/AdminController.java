package ra.com.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.com.modules.products.dto.request.ProductRequest;
import ra.com.modules.products.dto.response.ProductResponse;
import ra.com.modules.products.service.IProductService;
import ra.com.modules.products.validator.ProductValidator;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductValidator validator;
    @Autowired
    private IProductService productService;

    @GetMapping
    public String admin() {
        return "admin/index"; // tên view
    }

    @GetMapping("/category")
    public String category() {
        return "admin/category";
    }


    @GetMapping("/receipt")
    public String receipt() {
        System.out.println("receipt");
        return "admin/receipt"; }



    @GetMapping("/product")  // prooduct list
    public String product(@RequestParam(value = "page",defaultValue = "0") Integer page,@RequestParam(value = "limit",defaultValue = "3") Integer limit,Model model) {
        // phân trang
        // tổng số trang
        // sô luong sp trên trang
        // trang hiện tại (tính từ 0)
        long totalElements = productService.getTotalsElement();
        System.out.println("totalElements: "+totalElements);
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;

        model.addAttribute("products",productService.findByPagination(page,limit));
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("product",new ProductRequest());
        return "admin/product/product"; // tên view
    }
    @GetMapping("/user")
    public String user() {
        return "admin/user";
    }

    @GetMapping("/product/add")
    public String add(Model model){
        model.addAttribute("product",new ProductRequest());
        return "admin/product/product-add";
    }
    // product mananger
    @PostMapping("/product/add")
    public  String doAdd(@Valid @ModelAttribute("product") ProductRequest request, BindingResult result,Model model){
//         validator.validate(request,result);
        if (result.hasErrors()){
            model.addAttribute("product",request);
            return "admin/product/product-add";
        }
        productService.save(request);
        return "redirect:/admin/product"; //điều hướng theo đường dẫn
    }
    @PostMapping("/product/update")
    public String doUpdate(@ModelAttribute("product") ProductRequest request, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("product", request);
            return "admin/product/product-edit";
        }
        try {
            productService.save(request);
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while updating the product.");
            return "admin/product/product-edit";
        }
        return "redirect:/admin/product";
    }
    @GetMapping("/product/delete")
    public String doDelete(@RequestParam("id") Integer idDel){
        productService.deleteById(idDel);
        return "redirect:/admin/product"; //điều hướng theo đường dẫn
    }
    @GetMapping("/product/search")
    public String search(@RequestParam("keyword") String keyword,Model model){
        model.addAttribute("products",productService.searchByName(keyword));
        model.addAttribute("keyword",keyword);
        model.addAttribute("product",new ProductRequest());
        return "admin/product/product"; // tên view
    }

    @GetMapping("/product/edit/{id}")
    @ResponseBody
    public ProductResponse edit(@PathVariable Integer id){
        return productService.findById(id);
    }

}
