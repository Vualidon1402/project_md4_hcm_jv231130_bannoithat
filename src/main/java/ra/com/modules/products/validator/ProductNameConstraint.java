package ra.com.modules.products.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.com.modules.products.service.IProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class ProductNameConstraint implements ConstraintValidator<ProductNameUnique,String> {
  @Autowired
  private IProductService productService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
       // nếu hợp lệ trả về true
        // nếu ko thì false
        return  !productService.existByName(value);
    }
}
