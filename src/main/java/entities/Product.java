package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 200, nullable = false)
    private String Name;
    @ManyToOne
    @JoinColumn(name = "company_Id", nullable = false)
    private Company company;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories = new ArrayList<>();
}
