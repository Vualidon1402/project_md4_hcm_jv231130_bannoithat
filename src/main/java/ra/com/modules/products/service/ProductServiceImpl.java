package ra.com.modules.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ra.com.modules.products.Product;
import ra.com.modules.products.dao.IProductDao;
import ra.com.modules.products.dto.request.ProductRequest;
import ra.com.modules.products.dto.response.ProductResponse;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{
    private static final String uploadFolder = "C:\\Users\\ADMIN\\Desktop\\project_md4_hcm_jv231130_banbanghe\\src\\main\\webapp\\uploads\\";
    @Autowired
    private IProductDao productDao;
    @Autowired
    private ServletContext servletContext;
    @Override
    public List<ProductResponse> findAll() {
        List<Product> list = productDao.findAll();
        return list.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByPagination(Integer page, Integer limit) {
        return productDao.findByPagination(page,limit);
    }

    @Override
    public long getTotalsElement() {
        return productDao.getTotalsElement();
    }

    @Override
    public boolean existByName(String name) {
        return productDao.existByName(name);
    }

    @Override
    public List<ProductResponse> searchByName(String keyword) {
        return productDao.searchByName(keyword).stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Integer id) {
        return new ProductResponse(productDao.findById(id));
    }

    @Override
    public void save(ProductRequest request) {
        // chuyển đổi
        Product product = new Product();
         if (request.getId() != null){
             // neu laf chuc nang cap nhap
             product= productDao.findById(request.getId());
         }else {
            product.setCreatedAt(new Date());
            product.setIsDeleted(false);
         }
         product.setName(request.getName());
         product.setDescription(request.getDescription());
         product.setPrice(request.getPrice());
         product.setStock(request.getStock());

        // upload mới
        if (request.getFile()!=null && request.getFile().getSize()!=0){
            String uploadPath = servletContext.getRealPath("/uploads");
            File folder = new File(uploadPath);
            if (!folder.exists()){
                folder.mkdirs();
            }
            String fileName = request.getFile().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadPath+File.separator+fileName));
                FileCopyUtils.copy(request.getFile().getBytes(),new File(uploadFolder+fileName));
                product.setImage("/uploads/"+fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        productDao.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productDao.deleteById(id);
    }
}
