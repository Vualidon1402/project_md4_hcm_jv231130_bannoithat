package ra.com.modules.products.dao;


import ra.com.modules.products.Product;

import java.util.List;

public interface IProductDao {
    List<Product> findAll();
    List<Product> findByPagination(Integer page ,Integer size);
    List<Product> searchByName(String keyword);
    Product findById(Integer id);
    void save(Product product);  // vừa thêm moi vưa cap nhap
    void deleteById(Integer id);
    long getTotalsElement();
    boolean existByName(String name);
}
