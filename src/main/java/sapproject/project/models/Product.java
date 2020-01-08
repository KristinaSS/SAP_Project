package sapproject.project.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 884737769541155742L;


    public Product() {
    }

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
    @JoinColumn(name = "category_id")
    private Category category;

    @Basic
    @Column(name = "price")
    private float price;

    @Basic
    @Column(name = "min_price")
    private float minPrice;

    @Basic
    @Column(name = "discription")
    private String discription;

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product(String name, int quantity, Category category, float price) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }
}
