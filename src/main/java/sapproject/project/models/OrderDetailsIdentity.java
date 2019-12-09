package sapproject.project.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Embeddable
public class OrderDetailsIdentity implements Serializable {
    private static final long serialVersionUID = -6597649193605831768L;

    @NotNull
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "productId")
    private Product product;

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderId")
    @NotNull
    private Order order;

    public OrderDetailsIdentity(@NotNull Product product, @NotNull Order order) {
        this.product = product;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
