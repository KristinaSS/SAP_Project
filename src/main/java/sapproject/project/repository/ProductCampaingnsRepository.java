package sapproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapproject.project.models.AccountType;
import sapproject.project.models.ProductCampaigns;
import sapproject.project.models.ProductCampaignsFK;

@Repository
public interface ProductCampaingnsRepository extends JpaRepository<ProductCampaigns, ProductCampaignsFK> {
}
