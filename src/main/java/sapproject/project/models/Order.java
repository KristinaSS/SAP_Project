package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name = "order")
public class Order implements Serializable {
    private static final long serialVersionUID = 7696645133827053325L;

    public Order(String dateTime, Account client) {
        this.dateTime = dateTime;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Basic
    @Column(name = "date_time", nullable = false)
    private String dateTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Account client;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Account getClient() {
        return client;
    }

    public void setClient(Account client) {
        this.client = client;
    }

    /*    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetailsList;*/
}
