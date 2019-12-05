package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapproject.project.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
