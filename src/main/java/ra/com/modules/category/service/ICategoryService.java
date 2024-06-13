package ra.com.modules.category.service;


import ra.com.modules.category.Category;
import ra.com.modules.category.dto.request.CategoryRequest;
import ra.com.modules.category.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> findAll();

    List<Category> findByPagination(Integer page, Integer limit);

    List<CategoryResponse> searchByName(String keyword);

    CategoryResponse findById(Integer id);

    void save(CategoryRequest request);  // vừa thêm moi vưa cap nhap

    void deleteById(Integer id);

    long getTotalsElement();

    boolean existByName(String name);
}
