package sapproject.project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "campaign")
public class Campaign implements Serializable{

    private static final long serialVersionUID = 1888516181101543291L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private int accountTypeID;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "isActive", nullable = false)
    private Boolean isActive;


    @Basic
    @Column(name = "details", nullable = false)
    private String details;

    public int getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
