package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapproject.project.models.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}
