package ra.com.modules.category.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.com.modules.category.service.ICategoryService;
import ra.com.modules.products.service.IProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CategoryNameConstraint implements ConstraintValidator<CategoryNameUnique, String> {
    @Autowired
    private ICategoryService categoryService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // nếu hợp lệ trả về true
        // nếu ko thì false
        return !categoryService.existByName(value);
    }
}
