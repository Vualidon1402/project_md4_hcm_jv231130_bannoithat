package ra.com.modules.nhacungcap;


import lombok.*;
import ra.com.modules.products.Product;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "nhaCungCapList")
    private List<Product> products;
}

