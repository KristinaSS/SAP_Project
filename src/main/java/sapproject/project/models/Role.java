package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int accountTypeID;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rolesID")
    private List<AccountTypeRole> accountListFromRole;

    /*@ManyToMany(mappedBy = "rolesList") //the list in the other Class
    private List<AccountType> accountTypeList;*/
}
