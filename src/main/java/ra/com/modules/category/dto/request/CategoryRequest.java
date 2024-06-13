package ra.com.modules.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ra.com.modules.category.validator.CategoryNameUnique;

import javax.persistence.Id;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    private Integer id;
    @NotBlank(message = "Không được để trống")
    @CategoryNameUnique
    private String name;

    private MultipartFile file;

}
