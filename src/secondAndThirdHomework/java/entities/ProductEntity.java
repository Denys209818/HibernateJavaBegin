package entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tblProductEntity")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 200, nullable = false)
    private String Title;
    @Column(nullable = false)
    private String Description;
    @Column(nullable = false)
    private int Price;
    @ManyToOne
    @JoinColumn(name = "CategoryEntity_Id", nullable = false)
    private CategoryEntity category;
}
