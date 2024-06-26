package ra.com.modules.category;


import lombok.*;
import ra.com.modules.products.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    private Date createdAt;
    private Boolean isDeleted;
    @OneToMany(mappedBy = "category") // bn nghịch đao
    private List<Product> products;
}

