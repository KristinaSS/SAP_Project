package sapproject.project.models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductCampaignsFK implements Serializable {
    private static final long serialVersionUID = -6585563691398670803L;

    private int campaignId;
    private int productId;

    public ProductCampaignsFK() {
    }
    public ProductCampaignsFK(int campaignId, int productId){
        this.campaignId= campaignId;
        this.productId = productId;
    }

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int orderId) {
        this.campaignId = orderId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCampaignsFK that = (ProductCampaignsFK) o;
        return campaignId == that.campaignId &&
                productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignId, productId);
    }

}
