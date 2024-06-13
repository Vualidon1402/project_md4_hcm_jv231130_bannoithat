package ra.com.modules.category.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.com.modules.category.dto.request.CategoryRequest;
import ra.com.modules.category.service.ICategoryService;
import ra.com.modules.products.dto.request.ProductRequest;
import ra.com.modules.products.service.IProductService;


@Component
public class CategoryValidator implements Validator {
    @Autowired
    private ICategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductRequest p = (ProductRequest) target;
        if (categoryService.existByName(p.getName())) {
            errors.rejectValue("name", "ncjdhv", "tên da tồn tại !");
        }
    }
}
