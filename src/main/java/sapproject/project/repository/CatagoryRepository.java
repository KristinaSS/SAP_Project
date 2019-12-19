package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapproject.project.models.Category;

@Repository
public interface CatagoryRepository extends JpaRepository<Category, Integer> {
}
