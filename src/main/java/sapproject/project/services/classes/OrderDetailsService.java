package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;

import sapproject.project.models.OrderDetails;
import sapproject.project.models.OrderDetailsPK;
import sapproject.project.models.Product;
import sapproject.project.models.ProductCampaigns;
import sapproject.project.payload.ReportPayload;
import sapproject.project.repository.OrderDetailsRepository;
import sapproject.project.services.interfaces.IOrderDetailsService;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class OrderDetailsService{
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    ProductService productService;

    @Autowired
    private CampaignService campaignService;


    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    public List<ReportPayload> getSalesReport() {
        List<ReportPayload> reportPayloadList = new ArrayList<>();
        List<Product>reportedProducts = new ArrayList<>();
        for(OrderDetails orderDetails: findAll()){
            Product product = productService.getOne(orderDetails.getOrderdetailsPK().getProductId());

/*
            ProductCampaigns productCampaigns;
            productCampaigns = campaignService.findProductIfOnSale(product.getProductId());
            if(productCampaigns!=null)
                product.setPrice(productCampaigns.getPrice());
*/

            float sum  = 0;
            int quantity = 0;
            for (OrderDetails od: findAll()){
                if(reportedProducts.contains(product))
                    continue;
                if(od.getOrderdetailsPK().getProductId() == product.getProductId()){
                    sum += od.getSum();
                    quantity += od.getQuantity();
                }
            }
            if(!reportedProducts.contains(product)) {
                reportedProducts.add(product);

                ReportPayload payload = new ReportPayload();
                payload.setProductName(product.getName());
                payload.setQuantity(quantity);
                payload.setSum(sum);
                payload.setProductId(product.getProductId());
                reportPayloadList.add(payload);
            }
        }
        return reportPayloadList;
    }


    public Float calculate() {
        float total = 0;
        for(OrderDetails orderDetails: findAll()){
            total+=orderDetails.getSum();
        }
        return total;
    }
}
