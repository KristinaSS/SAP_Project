package sapproject.project.models;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountTypeRolePK implements Serializable {
    private static final long serialVersionUID = -8266645341864815228L;

    private int accountTypeID;
    private int rolesID;

    public AccountTypeRolePK(int orderId, int productId) {
        this.accountTypeID = orderId;
        this.rolesID = productId;
    }

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    public int getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    @ManyToOne
    @JoinColumn(name = "roles_id")
    public int getRolesID() {
        return rolesID;
    }

    public void setRolesID(int rolesID) {
        this.rolesID = rolesID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTypeRolePK that = (AccountTypeRolePK) o;
        return accountTypeID == that.accountTypeID &&
                rolesID == that.rolesID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTypeID, rolesID);
    }
}
