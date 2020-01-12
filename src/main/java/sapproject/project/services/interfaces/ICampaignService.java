package sapproject.project.services.interfaces;

import sapproject.project.models.Campaign;
import sapproject.project.models.ProductCampaigns;
import sapproject.project.payload.CampaignPayload;
import sapproject.project.payload.ProductCampaignPayload;

import java.util.List;

public interface ICampaignService {
    Campaign findActiveCampaign();

    void deleteProductInCampaign(ProductCampaignPayload payload);

    Campaign findCampaignByName(String campaignName);

    ProductCampaigns findProductIfOnSale(int productId);

    void addToCampaign(ProductCampaignPayload campaign);

    Campaign editCampaign(CampaignPayload campaign);

    Campaign createCampaign(CampaignPayload campaign);

    void deleteCampaignById(int campaignId);

    Campaign getCampaignById(int campaignId);

    List<CampaignPayload> findAll();
}
