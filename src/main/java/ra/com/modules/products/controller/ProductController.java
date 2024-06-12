package ra.com.modules.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ra.com.modules.products.dto.response.ProductResponse;
import ra.com.modules.products.service.IProductService;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public String test() {
        List<ProductResponse> products = productService.findAll();
//        ProductRequest p = new ProductRequest(null,"áodai",10.0,null,null,99,null);
//        productService.save(p);
        return "home";
    }
}
