package sapproject.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity(name = "orderdetails")
public class OrderDetails implements Serializable {
    private static final long serialVersionUID = -3056970932234138597L;

    @EmbeddedId
    private OrderDetailsPK orderdetailsPK;

    @Basic
    @Column(name = "price", nullable = false)
    private float price;

    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Basic
    @Column(name = "discount", nullable = false)
    private float discount;

    @Basic
    @Column(name = "sum", nullable = false)
    private float sum;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public OrderDetailsPK getOrderdetailsPK() {
        return orderdetailsPK;
    }

    public void setOrderdetailsPK(OrderDetailsPK orderdetailsPK) {
        this.orderdetailsPK = orderdetailsPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
/*

        @Id
        @ManyToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "order_id")
        private Order order;

        @Id
        @ManyToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "product_id")
        private Product product;
     */