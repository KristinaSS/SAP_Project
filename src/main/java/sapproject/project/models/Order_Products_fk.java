package sapproject.project.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

public class Order_Products_fk {
    private int productId;
    private int orderId;

    @Column(name = "productId")
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column(name = "orderId")
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order_Products_fk that = (Order_Products_fk) o;
        return productId == that.productId &&
                orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
