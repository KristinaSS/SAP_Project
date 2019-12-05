package sapproject.project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "catagory")
public class Catagory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catagory_id")
    private int categoryId;

    @Basic
    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "catagory_id")
    private Catagory parentCategory;
}
