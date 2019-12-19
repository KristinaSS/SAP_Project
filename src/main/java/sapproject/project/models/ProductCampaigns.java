package sapproject.project.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "product_campaigns")
public class ProductCampaigns implements Serializable {
    private static final long serialVersionUID = 827726027981851544L;

    @EmbeddedId
    private  ProductCampaignsFK productCampaignsFK;

    @Basic
    @Column(name = "product_price", nullable = false)
    private float price;

    @Basic
    @Column(name = "discount_product", nullable = false)
    private float discount;


    public ProductCampaignsFK getProductCampaignsFK() {
        return productCampaignsFK;
    }

    public void setProductCampaignsFK(ProductCampaignsFK productCampaignsFK) {
        this.productCampaignsFK = productCampaignsFK;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
