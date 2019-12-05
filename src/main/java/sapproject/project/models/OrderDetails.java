package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "orderdetails")
public class OrderDetails {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Basic
    @Column(name = "discount", nullable = false)
    private float discount;

    @Basic
    @Column(name = "sum", nullable = false)
    private float sum;
}
