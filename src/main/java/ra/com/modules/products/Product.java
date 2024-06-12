package ra.com.modules.products;

import lombok.*;
import ra.com.modules.category.Category;
import ra.com.modules.nhacungcap.NhaCungCap;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
// ánh xạ với bảng nào tron db
// cac mối quan he
// 1 -1
// 1 - n
// n -1
// n - n -> 1 - n - Trung gian - n -1



@Table(name = "Product")
@Entity // đây là 1 thực thể ánh xạ
public class Product {
    @Id // khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;
    private String description;
    private String image ;
    private Integer stock;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    // fetch : lấy , load dữ liêu
    // Mặc đinh ManyToOne , và OnetoOne là Eager (load ngay lập tức)
    // Mặc đinh OneToMany , và ManytoMany là Lazy (ko tải dữ lệu cun với lớp)

    // cascade : lan truyên hành vi , thao tác trên entity tới scacs thực thể quan hệ với n
    @ManyToOne
    @JoinColumn(name = "category_id") // chủ thể
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "nhacungcap_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "nhacungcap_id")
    )
    private List<NhaCungCap> nhaCungCapList;
}
