package sapproject.project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartProducts implements Serializable {
    private static final long serialVersionUID = -4993938723418672269L;

    private int quantity;
    private Cart cart;
    private Product product;
    private int cartProductID;

    public CartProducts(int quantity, Cart cart, Product product) {
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }

    public CartProducts() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    public int getCartProductID() {
        return cartProductID;
    }

    public void setCartProductID(int cartProductID) {
        this.cartProductID = cartProductID;
    }

    @ManyToOne
    @JoinColumn(name = "cart_id")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
