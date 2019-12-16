package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class AccounTypeRolePK implements Serializable {
    private static final long serialVersionUID = 1444675674021249986L;

    @ManyToOne
    @JoinColumn(name = "roles_id")
    private int roleID;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private int AccountTypeId;

    public AccounTypeRolePK(int roleID, int accountTypeId) {
        this.roleID = roleID;
        AccountTypeId = accountTypeId;
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
