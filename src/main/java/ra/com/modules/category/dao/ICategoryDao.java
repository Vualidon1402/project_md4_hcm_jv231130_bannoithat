package ra.com.modules.category.dao;


import ra.com.modules.category.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> findAll();

    List<Category> findByPagination(Integer page, Integer size);

    List<Category> searchByName(String keyword);

    Category findById(Integer id);

    void save(Category category);  // vừa thêm moi vưa cap nhap

    void deleteById(Integer id);

    long getTotalsElement();

    boolean existByName(String name);
}
