package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 200, nullable = false)
    private String Title;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "category_product",
            joinColumns = { @JoinColumn(referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "Id")})
    private List<Product> products = new ArrayList<>();

}
