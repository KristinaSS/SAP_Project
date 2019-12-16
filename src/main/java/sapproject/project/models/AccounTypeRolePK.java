package sapproject.project.models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccounTypeRolePK implements Serializable {
    private static final long serialVersionUID = 1444675674021249986L;

    private int roleID;
    private int AccountTypeId;

    public AccounTypeRolePK(int roleID, int accountTypeId) {
        this.roleID = roleID;
        AccountTypeId = accountTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "roles_id")
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    public int getAccountTypeId() {
        return AccountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.AccountTypeId = accountTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsPK that = (OrderDetailsPK) o;
        return roleID == that.getOrderId() &&
                AccountTypeId == that.getProductId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleID, AccountTypeId);
    }
}
