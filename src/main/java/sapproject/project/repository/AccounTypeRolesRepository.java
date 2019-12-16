package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sapproject.project.models.AccounTypeRole;
import sapproject.project.models.AccounTypeRolePK;

public interface AccounTypeRolesRepository extends JpaRepository<AccounTypeRole, AccounTypeRolePK> {
}
