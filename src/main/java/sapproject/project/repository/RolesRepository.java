package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sapproject.project.models.Role;

public interface RolesRepository extends JpaRepository<Role, Integer> {
}
