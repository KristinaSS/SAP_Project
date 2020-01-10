package sapproject.project.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.models.Campaign;
import sapproject.project.models.Product;
import sapproject.project.models.ProductCampaigns;
import sapproject.project.models.ProductCampaignsFK;
import sapproject.project.payload.CampaignPayload;
import sapproject.project.payload.ProductCampaignPayload;
import sapproject.project.repository.CampaignRepository;
import sapproject.project.repository.ProductCampaingnsRepository;
import sapproject.project.services.interfaces.IProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ProductCampaingnsRepository productCampaingnsRepository;

    @Autowired
    private ProductService productService;

    public List<CampaignPayload> findAll() {
        List<CampaignPayload> payloads = new ArrayList<>();
        CampaignPayload campaignPayload;
        for (Campaign campaign : campaignRepository.findAll()) {
            campaignPayload = new CampaignPayload();
            campaignPayload.setId(String.valueOf(campaign.getCampaignId()));
            campaignPayload.setIsActive(String.valueOf(campaign.getActive()));
            campaignPayload.setName(campaign.getName());
            campaignPayload.setDetails(campaign.getDetails());

            payloads.add(campaignPayload);
        }
        return payloads;
    }

    public Campaign getCampaignById(int campaignId) {
        for (Campaign campaign : campaignRepository.findAll()) {
            if (campaign.getCampaignId() == campaignId)
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
        makeOnlyOneCampaignActive(result);

        return campaignRepository.save(result);
    }

    private Campaign initializeCampaign(Campaign createdCampaign, CampaignPayload campaignPayload) {
        if (campaignPayload.getId() != null)
            createdCampaign.setCampaignId(Integer.parseInt(campaignPayload.getId()));
        createdCampaign.setActive(Boolean.parseBoolean(campaignPayload.getIsActive()));
        createdCampaign.setDetails(campaignPayload.getDetails());
        createdCampaign.setName(campaignPayload.getName());

        return createdCampaign;
    }

    private void makeOnlyOneCampaignActive(Campaign campaign) {
        if (campaign.getActive()) {
            for (Campaign c : campaignRepository.findAll()) {
                if (c.getCampaignId() == campaign.getCampaignId())
                    continue;
                c.setActive(false);
                campaignRepository.save(c);
            }
        }
    }

    public Campaign editCampaign(CampaignPayload campaign) {
        Campaign editedCampaign = getCampaignById(Integer.parseInt(campaign.getId()));
        Campaign result = initializeCampaign(editedCampaign, campaign);

        makeOnlyOneCampaignActive(result);

        return campaignRepository.save(result);
    }

    public void addToCampaign(ProductCampaignPayload campaign) {
        int productId = Integer.parseInt(campaign.getProductId());
        Product product = productService.getOne(productId);
        Campaign campaignByName = getCampaignByName(campaign.getCampaignName());
        if (campaignByName == null)
            return;
        int campaignId = campaignByName.getCampaignId();
        float discount = Float.parseFloat(campaign.getPrice())/100f;
        float priceDuringCampaign = product.getPrice() - (product.getPrice()*discount);

        ProductCampaignsFK fk = new ProductCampaignsFK();
        fk.setCampaignId(campaignId);
        fk.setProductId(productId);

        ProductCampaigns productCampaigns = new ProductCampaigns();
        productCampaigns.setProductCampaignsFK(fk);
        productCampaigns.setPrice(priceDuringCampaign);

        productCampaingnsRepository.save(productCampaigns);
    }

    public ProductCampaigns findProductIfOnSale(int productId) {
        Campaign campaign;
        for (ProductCampaigns productCampaigns : productCampaingnsRepository.findAll()) {
            campaign = getCampaignById(productCampaigns.getProductCampaignsFK().getCampaignId());
            if (campaign.getActive() && productCampaigns.getProductCampaignsFK().getProductId() == productId) {
                return productCampaigns;
            }
        }
        return null;
    }

    private Campaign getCampaignByName(String campaignName) {
        for (Campaign campaign : campaignRepository.findAll()) {
            if (campaign.getName().equals(campaignName))
                return campaign;
        }
        return null;
    }

    public Campaign findCampaignByName(String campaignName) {
        for (Campaign campaign : campaignRepository.findAll()) {
            if (campaign.getName().equals(campaignName)) {
                return campaign;
            }
        }
        return null;
    }

    public void deleteProductInCampaign(ProductCampaignPayload payload) {
        Campaign campaign = findCampaignByName(payload.getCampaignName());
        for (ProductCampaigns productCampaigns : productCampaingnsRepository.findAll()) {
            if (productCampaigns.getProductCampaignsFK().getCampaignId() == campaign.getCampaignId()
                    && productCampaigns.getProductCampaignsFK().getProductId()
                    == Integer.parseInt(payload.getProductId())) {
                productCampaingnsRepository.delete(productCampaigns);
            }
        }
    }

    public Campaign findActiveCampaign(){
        for(Campaign campaign: campaignRepository.findAll()){
            if(campaign.getActive())
                return campaign;
        }
        return null;
    }
}
