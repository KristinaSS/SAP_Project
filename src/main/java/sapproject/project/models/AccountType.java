package sapproject.project.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private String name;


    private List<Role> roleList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "accounttyperoles",
            joinColumns = {
                    @JoinColumn(name = "accountTypeID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "roleId", nullable = false, updatable = false)
            })
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
