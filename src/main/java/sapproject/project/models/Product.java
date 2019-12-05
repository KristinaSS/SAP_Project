package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int productId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "catagory_id")
    private Catagory catagory;

    @Basic
    @Column(name = "price")
    private float price;
}
