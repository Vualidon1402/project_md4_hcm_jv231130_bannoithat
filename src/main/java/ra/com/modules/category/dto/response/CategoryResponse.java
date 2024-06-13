package ra.com.modules.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.com.modules.category.Category;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data // bao gồm getter và setter
public class CategoryResponse {
    private Integer id;
    private String name;
    private String image;
    private Date createdAt;


    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.image = category.getImage();
        this.createdAt = category.getCreatedAt();
    }
}
