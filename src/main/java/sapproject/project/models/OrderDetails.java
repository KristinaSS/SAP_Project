package sapproject.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
@IdClass(OrderDetails.class)
@Entity(name = "orderdetails")
public class OrderDetails implements Serializable {
    private static final long serialVersionUID = -3056970932234138597L;
    
    @EmbeddedId
    private OrderDetailsPK orderdetailsPK;

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