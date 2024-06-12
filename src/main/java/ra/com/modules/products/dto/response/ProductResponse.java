package ra.com.modules.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.com.modules.products.Product;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data // bao gồm getter và setter
public class ProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String image ;
    private Integer stock;
    private Date createdAt;

    public ProductResponse(Product product) {
        this.id  = product.getId();
        this.name = product.getName();
        this.image = product.getImage();
        this.createdAt = product.getCreatedAt();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.price = product.getPrice();
    }
}
