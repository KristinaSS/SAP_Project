package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapproject.project.models.AccountType;
import sapproject.project.models.Cart;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
