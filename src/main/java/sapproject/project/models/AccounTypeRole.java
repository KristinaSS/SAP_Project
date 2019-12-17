package sapproject.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "accounttyperoles")
public class AccounTypeRole implements Serializable {

    private static final long serialVersionUID = 4421103251813268614L;

    @EmbeddedId
    private AccounTypeRolePK accounTypeRolePK;
}
