package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Product;
import sapproject.project.models.ProductCampaigns;
import sapproject.project.payload.EditProductPayload;
import sapproject.project.payload.ProductPayload;
import sapproject.project.services.interfaces.ICampaignService;
import sapproject.project.services.interfaces.IProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICampaignService campaignService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@Valid @RequestBody EditProductPayload payload) {
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
        return productService.createOne(product);
    }

    @PostMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@Valid @RequestBody ProductPayload product) {
        return productService.updateByID(product);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@Valid @RequestBody String productId) {
        productService.deleteByID(Integer.parseInt(productId));
    }

    @PostMapping("/filteredByCategory")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByCategory(@Valid @RequestBody String category) {
        return productService.findAllProductsByCategory(category);
    }

    @PostMapping("/filteredByKeyword")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByKeyword(@Valid @RequestBody String keyword) {
        return productService.findAllProductsByKeyword(keyword);
    }

    @PostMapping("/filteredByCampaign")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByCampaign(@Valid @RequestBody String campaignName) {
        return productService.findAllProductsOnSale(campaignName);
    }

    @GetMapping("/outOfStock")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByCategory() {
        return productService.findAllOutOfStockProducts();
    }
}
