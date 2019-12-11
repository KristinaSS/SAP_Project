package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapproject.project.models.OrderDetails;
import sapproject.project.models.OrderDetailsPK;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsPK> {
}
