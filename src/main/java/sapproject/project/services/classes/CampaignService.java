package sapproject.project.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.models.Campaign;
import sapproject.project.payload.CampaignPayload;
import sapproject.project.payload.ProductCampaignPayload;
import sapproject.project.repository.CampaignRepository;

import java.util.List;

@Service
public class CampaignService {
    @Autowired
    CampaignRepository campaignRepository;

    public List<Campaign> findAll(){
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(int campaignId) {
        for(Campaign campaign: findAll()){
            if(campaign.getCampaignId() == campaignId)
                return campaign;
        }
        return null;
    }

    public void deleteCampaignById(int campaignId) {
        Campaign campaign = getCampaignById(campaignId);
        campaignRepository.delete(campaign);
    }

    public Campaign createCampaign(CampaignPayload campaign) {
        Campaign createdCampaign = new Campaign();
        Campaign result = initializeCampaign(createdCampaign, campaign);

        return campaignRepository.save(result);
    }

    private Campaign initializeCampaign(Campaign createdCampaign, CampaignPayload campaignPayload){
        if(campaignPayload.getId()!= null)
            createdCampaign.setCampaignId(Integer.parseInt(campaignPayload.getId()));
        createdCampaign.setActive(Boolean.parseBoolean(campaignPayload.getIsActive()));
        createdCampaign.setDetails(campaignPayload.getDetails());
        createdCampaign.setName(campaignPayload.getName());

        return createdCampaign;
    }

    public Campaign editCampaign(CampaignPayload campaign) {
        Campaign editedCampaign = getCampaignById(Integer.parseInt(campaign.getId()));
        Campaign result = initializeCampaign(editedCampaign, campaign);

        return campaignRepository.save(result);
    }

    public void addToCampaign(ProductCampaignPayload campaign) {

    }
}
