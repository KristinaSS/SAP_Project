package sapproject.project.models;


import lombok.*;
import sapproject.project.utils.MD5;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@NoArgsConstructor
@Entity(name = "account")
public class Account implements Serializable {
    private static final long serialVersionUID = 7695450117825003302L;

    private int accID;
    private AccountType accountType;
    private String name;
    private String email;
    private transient String password;

    //todo fix
    /*
    private List<Review> reviewList = new ArrayList<>();
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_type_id")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Basic
    @Column(name = "first_name")
    @Size(min = 4, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = false)
    @Size(min = 4, max = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false)
    @Size(min = 4, max = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
/*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    @JsonIgnoreProperties("account")
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

 */
}
