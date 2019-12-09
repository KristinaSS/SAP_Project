package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 884737769541155742L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
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

    public Product(String name, int quantity, Catagory catagory, float price) {
        this.name = name;
        this.quantity = quantity;
        this.catagory = catagory;
        this.price = price;
    }
}
