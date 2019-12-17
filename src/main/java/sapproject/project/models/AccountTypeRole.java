package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "accounttyperoles")
public class AccountTypeRole implements Serializable {
    private static final long serialVersionUID = 512844024227859895L;
    /*@EmbeddedId
    AccountTypeRolePK accountTypeRolePK;*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountTypeID;

    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Role rolesID;
}
