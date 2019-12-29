package sapproject.project.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

public class CartProducts implements Serializable {
    private static final long serialVersionUID = -4993938723418672269L;

    @EmbeddedId
    private CartProductsFK cartProductsFK;

    @Basic
    @Column(name = "quantity", nullable = false)
    private int quanity;

    public CartProductsFK getCartProductsFK() {
        return cartProductsFK;
    }

    public void setCartProductsFK(CartProductsFK cartProductsFK) {
        this.cartProductsFK = cartProductsFK;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
}
