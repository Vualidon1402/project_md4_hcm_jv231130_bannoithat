package ra.com.modules.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ra.com.modules.category.Category;
import ra.com.modules.category.dao.ICategoryDao;
import ra.com.modules.category.dto.request.CategoryRequest;
import ra.com.modules.category.dto.response.CategoryResponse;


import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {
    private static final String uploadFolder = "C:\\Users\\ADMIN\\Desktop\\project_md4_hcm_jv231130_bannoithat\\src\\main\\webapp\\uploads\\";
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private ServletContext servletContext;

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> list = categoryDao.findAll();
        return list.stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<Category> findByPagination(Integer page, Integer limit) {
        return categoryDao.findByPagination(page, limit);
    }

    @Override
    public long getTotalsElement() {
        return categoryDao.getTotalsElement();
    }

    @Override
    public boolean existByName(String name) {
        return categoryDao.existByName(name);
    }

    @Override
    public List<CategoryResponse> searchByName(String keyword) {
        return categoryDao.searchByName(keyword).stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findById(Integer id) {
        return new CategoryResponse(categoryDao.findById(id));
    }

    @Override
    public void save(CategoryRequest request) {
        // chuyển đổi
        Category category = new Category();
        if (request.getId() != null) {
            // neu laf chuc nang cap nhap
            category = categoryDao.findById(request.getId());
        } else {
            category.setCreatedAt(new Date());
            category.setIsDeleted(false);
        }
        category.setName(request.getName());

        // upload mới
        if (request.getFile() != null && request.getFile().getSize() != 0) {
            String uploadPath = servletContext.getRealPath("/uploads");
            File folder = new File(uploadPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String fileName = request.getFile().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getFile().getBytes(), new File(uploadPath + File.separator + fileName));
                FileCopyUtils.copy(request.getFile().getBytes(), new File(uploadFolder + fileName));
                category.setImage("/uploads/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ;
        categoryDao.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryDao.deleteById(id);
    }
}
