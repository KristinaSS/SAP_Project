package sapproject.project.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.CampaignNotUniqueName;
import sapproject.project.exceptions.DiscountBelowMinimalPriceException;
import sapproject.project.exceptions.ListSizeIsZero;
import sapproject.project.models.Campaign;
import sapproject.project.models.Product;
import sapproject.project.models.ProductCampaigns;
import sapproject.project.models.ProductCampaignsFK;
import sapproject.project.payload.CampaignPayload;
import sapproject.project.payload.ProductCampaignPayload;
import sapproject.project.repository.CampaignRepository;
import sapproject.project.repository.ProductCampaingnsRepository;
import sapproject.project.services.interfaces.ICampaignService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService implements ICampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ProductCampaingnsRepository productCampaingnsRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<CampaignPayload> findAll() {
        List<Campaign> campaigns = campaignRepository.findAll();
        if(campaigns.size() == 0)
            throw new ListSizeIsZero("campaigns");
        List<CampaignPayload> payloads = new ArrayList<>();
        CampaignPayload campaignPayload;
        for (Campaign campaign : campaigns) {
            campaignPayload = new CampaignPayload();
            campaignPayload.setId(String.valueOf(campaign.getCampaignId()));
            campaignPayload.setIsActive(String.valueOf(campaign.getActive()));
            campaignPayload.setName(campaign.getName());
            campaignPayload.setDetails(campaign.getDetails());

            payloads.add(campaignPayload);
        }
        return payloads;
    }

    @Override
    public Campaign getCampaignById(int campaignId) {
        for (Campaign campaign : campaignRepository.findAll()) {
            if (campaign.getCampaignId() == campaignId)
                return campaign;
        }
        return null;
    }

    @Override
    public void deleteCampaignById(int campaignId) {
        Campaign campaign = getCampaignById(campaignId);
        campaignRepository.delete(campaign);
    }

    @Override
    public Campaign createCampaign(CampaignPayload campaign) {
        Campaign isUnique = getCampaignByName(campaign.getName());

        if(isUnique!=null)
            throw new CampaignNotUniqueName(campaign.getName());

        Campaign createdCampaign = new Campaign();
        Campaign result = initializeCampaign(createdCampaign, campaign);
        makeOnlyOneCampaignActive(result);

        return campaignRepository.save(result);
    }

    @Override
    public Campaign editCampaign(CampaignPayload campaign) {
        Campaign editedCampaign = getCampaignById(Integer.parseInt(campaign.getId()));

        Campaign isUnique = getCampaignByName(editedCampaign.getName());
        assert isUnique != null;
        if(!isUnique.getName().equals(campaign.getName())){
            isUnique = getCampaignByName(campaign.getName());
            if(isUnique!= null)
                throw new CampaignNotUniqueName(campaign.getName());        }

        Campaign result = initializeCampaign(editedCampaign, campaign);

        makeOnlyOneCampaignActive(result);

        return campaignRepository.save(result);
    }

    @Override
    public void addToCampaign(ProductCampaignPayload campaign) {
        int productId = Integer.parseInt(campaign.getProductId());
        Product product = productService.getOne(productId);
        Campaign campaignByName = getCampaignByName(campaign.getCampaignName());
        if (campaignByName == null)
            return;
        int campaignId = campaignByName.getCampaignId();
        float discount = Float.parseFloat(campaign.getPrice()) / 100f;
        float priceDuringCampaign = product.getPrice() - (product.getPrice() * discount);

        if(priceDuringCampaign<product.getMinPrice())
            throw new DiscountBelowMinimalPriceException(product.getName());

        ProductCampaignsFK fk = new ProductCampaignsFK();
        fk.setCampaignId(campaignId);
        fk.setProductId(productId);

        ProductCampaigns productCampaigns = new ProductCampaigns();
        productCampaigns.setProductCampaignsFK(fk);
        productCampaigns.setPrice(priceDuringCampaign);

        productCampaingnsRepository.save(productCampaigns);
    }

    @Override
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

    @Override
    public Campaign findCampaignByName(String campaignName) {
        for (Campaign campaign : campaignRepository.findAll()) {
            if (campaign.getName().equals(campaignName)) {
                return campaign;
            }
        }
        return null;
    }

    @Override
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

    @Override
    public Campaign findActiveCampaign() {
        for (Campaign campaign : campaignRepository.findAll()) {
            if (campaign.getActive())
                return campaign;
        }
        return null;
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

    private Campaign getCampaignByName(String campaignName) {
        for (Campaign campaign : campaignRepository.findAll()) {
            if (campaign.getName().equals(campaignName))
                return campaign;
        }
        return null;
    }

}
