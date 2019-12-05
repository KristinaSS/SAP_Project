package sapproject.project.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity(name = "accounttype")
public class AccountType implements Serializable {

    private static final long serialVersionUID = 3521498010964665376L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private int accountTypeID;

    @Basic
    @Column(name = "name", nullable = false)
    @NotNull
    @Size(min = 4, max = 100)
    private String name;


/*    private List<Account> accountList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "account_type_id")
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }*/
}
