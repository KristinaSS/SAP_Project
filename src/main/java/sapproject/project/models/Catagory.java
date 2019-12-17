package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "catagory")
public class Catagory implements Serializable {
    private static final long serialVersionUID = 1743321812477439024L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catagory_id")
    private int categoryId;

    @Basic
    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id")
    private Catagory parentCategory;


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catagory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Catagory parentCategory) {
        this.parentCategory = parentCategory;
    }
}
