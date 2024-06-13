package ra.com.modules.products.service;

import ra.com.modules.products.Product;
import ra.com.modules.products.dto.request.ProductRequest;
import ra.com.modules.products.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> findAll();
    List<Product> findByPagination(Integer page , Integer limit);
    List<ProductResponse> searchByName(String keyword);
    ProductResponse findById(Integer id);
    void save(ProductRequest request);  // vừa thêm moi vưa cap nhap
    void deleteById(Integer id);
    long getTotalsElement();
    boolean existByName(String name);
}
