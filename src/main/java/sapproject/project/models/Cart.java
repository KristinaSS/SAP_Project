package sapproject.project.models;

import javax.persistence.*;
import java.io.Serializable;

public class Cart implements Serializable {
    private static final long serialVersionUID = -3818181978943661953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    private Account account;

    @ManyToOne
    @JoinColumn(name = "account_id")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
