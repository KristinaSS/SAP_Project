package sapproject.project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = -3818181978943661953L;

    public Cart() {
    }

    public Cart(Account account) {
        this.account = account;
    }

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

    @ManyToOne
    @JoinColumn(name = "accounts_id")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
