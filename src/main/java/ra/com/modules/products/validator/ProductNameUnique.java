package ra.com.modules.products.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = ProductNameConstraint.class
)
public @interface ProductNameUnique {
    // kiểu annotation / decorator
    String message() default "Ten da ton tai";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
