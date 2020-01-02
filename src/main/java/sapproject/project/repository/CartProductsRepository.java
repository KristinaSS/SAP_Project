package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapproject.project.models.AccountType;
import sapproject.project.models.CartProducts;



@Repository
public interface CartProductsRepository extends JpaRepository<CartProducts, Integer> {
}
