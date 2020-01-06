package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapproject.project.models.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
}
