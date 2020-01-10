package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Product;
import sapproject.project.models.ProductCampaigns;
import sapproject.project.models.ProductCampaignsFK;
import sapproject.project.payload.EditProductPayload;
import sapproject.project.payload.ProductCampaignPayload;
import sapproject.project.payload.ProductPayload;
import sapproject.project.services.classes.CampaignService;
import sapproject.project.services.classes.ProductService;
import sapproject.project.services.interfaces.IProductService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        //log.debug("REST request to get all Products.");
        return productService.findAll();
    }

    @PostMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@Valid @RequestBody EditProductPayload payload) {
        //log.debug("REST request to get Product : {}", id);
        System.out.println("id of product: " + payload.getProductId());

        Product product = productService.getOne(Integer.parseInt(payload.getProductId()));

        ProductCampaigns productCampaigns;
        productCampaigns = campaignService.findProductIfOnSale(product.getProductId());
        if (productCampaigns != null && !payload.getType().equals("edit") && !payload.getType().equals("campaign"))
            product.setPrice(productCampaigns.getPrice());

        return product;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody ProductPayload product) {
        //log.debug("REST request to save Product : {}", product);

        return productService.createOne(product);
    }

    @PostMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@Valid @RequestBody ProductPayload product) {
        //log.debug("REST request to update Product : {}", ID);
        return productService.updateByID(product);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@Valid @RequestBody String productId) {
        //log.debug("REST request to delete Product : {}", ID);
        productService.deleteByID(Integer.parseInt(productId));
    }

    @PostMapping("/filteredByCategory")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByCategory(@Valid @RequestBody String category) {
        //log.debug("REST request to save Product : {}", product);
        return productService.findAllProductsByCategory(category);
    }

    @PostMapping("/filteredByKeyword")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByKeyword(@Valid @RequestBody String keyword) {
        //log.debug("REST request to save Product : {}", product);
        return productService.findAllProductsByKeyword(keyword);
    }

    @PostMapping("/filteredByCampaign")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByCampaign(@Valid @RequestBody String campaignName) {
        //log.debug("REST request to save Product : {}", product);
        return productService.findAllProductsOnSale(campaignName);
    }

    @GetMapping("/outOfStock")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByCategory() {
        //log.debug("REST request to save Product : {}", product);
        return productService.findAllOutOfStockProducts();
    }
}
