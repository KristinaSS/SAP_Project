package sapproject.project.models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartProductsFK implements Serializable {
    private static final long serialVersionUID = 1596105392370917172L;

    private int cartId;
    private int productId;

    public CartProductsFK(int orderId, int productId) {
        this.cartId = orderId;
        this.productId = productId;
    }

    public CartProductsFK() {
    }

    @ManyToOne
    @JoinColumn(name = "cart_id")
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductsFK that = (CartProductsFK) o;
        return cartId == that.cartId &&
                productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }
}
