package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.exceptions.ListSizeIsZero;
import sapproject.project.exceptions.RegularLessThanMinimalPrice;
import sapproject.project.models.Campaign;
import sapproject.project.models.Category;
import sapproject.project.models.Product;
import sapproject.project.models.ProductCampaigns;
import sapproject.project.payload.ProductPayload;
import sapproject.project.repository.ProductCampaingnsRepository;
import sapproject.project.repository.ProductRepository;
import sapproject.project.services.interfaces.IProductService;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CatagoryService catagoryService;


    @Autowired
    CampaignService campaignService;

    @Autowired
    private ProductCampaingnsRepository productCamapaignsRepository;


    @Override
    public List<Product> findAllProductsByCategory(String categoryName) {
        Category category = catagoryService.findCategoryByName(categoryName);
        List<Product> filteredList = new ArrayList<>();
        ProductCampaigns productCampaigns;

        int catID = 0;
        if (category == null) {
            return filteredList;//todo fix
        } else {
            catID = category.getCategoryId();
        }
        for (Product product : productRepository.findAll()) {
            if (product.getCategory().getCategoryId() == catID && product.getQuantity() > 0) {
                productCampaigns = campaignService.findProductIfOnSale(product.getProductId());
                if (productCampaigns != null)
                    product.setPrice(productCampaigns.getPrice());

                filteredList.add(product);
            }
        }
        return filteredList;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        if(products.size() == 0)
            throw new ListSizeIsZero("products");
        Product pr;
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                pr = findProductOnSale(product);
                if (pr == null)
                    productList.add(product);
                else
                    productList.add(pr);
            }
        }
        return productList;
    }

    @Override
    public Product getOne(int Id) {
        return productRepository.findById(Id).orElseGet(() -> {
            try {
                throw new EntityNotFoundException(Product.class);
            } catch (EntityNotFoundException e) {
                // log.warn("A product with this Id has not been found:  {}", Id);
            }
            return null;
        });
    }

    @Override
    public Product createOne(ProductPayload entity) {
        Product product = createProductObject(entity);
        return productRepository.save(product);
    }

    @Override
    public void deleteByID(int ID) {
        Product catagory = getOne(ID);
        if (catagory == null) {
            try {
                throw new EntityNotFoundException(Product.class);
            } catch (EntityNotFoundException e) {
                //log.warn("Product not found: {}", ID);
            }
            return;
        }
        //log.info("Deleted product: {} ",ID);
        productRepository.delete(catagory);

    }

    @Override
    public Product updateByID(ProductPayload payload) {
        Product product = findProductById(Integer.parseInt(payload.getId()));

        Product result = updateProductMembers(product, payload);

        return productRepository.save(result);
    }

    @Override
    public List<Product> findAllOutOfStockProducts() {
        List<Product> filteredList = new ArrayList<>();
        ProductCampaigns productCampaigns;
        for (Product product : productRepository.findAll()) {
            if (product.getQuantity() == 0) {
                productCampaigns = campaignService.findProductIfOnSale(product.getProductId());
                if (productCampaigns != null)
                    product.setPrice(productCampaigns.getPrice());
                filteredList.add(product);

            }
        }
        return filteredList;
    }

    @Override
    public List<Product> findAllProductsByKeyword(String keyword) {
        List<Product> filteredList = new ArrayList<>();
        ProductCampaigns productCampaigns;
        for (Product product : productRepository.findAll()) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) && product.getQuantity() > 0) {
                productCampaigns = campaignService.findProductIfOnSale(product.getProductId());
                if (productCampaigns != null)
                    product.setPrice(productCampaigns.getPrice());
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    @Override
    public List<Product> findAllProductsOnSale(String campaignName) {
        Campaign campaign;
        if (campaignName.equals("null")) {
            campaign = campaignService.findActiveCampaign();
        } else {
            campaign = campaignService.findCampaignByName(campaignName);
        }
        if(campaign==null)
            return new ArrayList<>();

        List<Product> filteredList = new ArrayList<>();
        ProductCampaigns productCampaigns1;
        Product product;

        for (ProductCampaigns productCampaigns : productCamapaignsRepository.findAll()) {
            if (productCampaigns.getProductCampaignsFK().getCampaignId() == campaign.getCampaignId()) {
                productCampaigns1 = campaignService.findProductIfOnSale(productCampaigns.getProductCampaignsFK().getProductId());
                product = findProductById(productCampaigns.getProductCampaignsFK().getProductId());
                if (productCampaigns1 != null && product != null) {
                    product.setPrice(productCampaigns1.getPrice());
                }
                assert product != null;
                if(product.getQuantity()==0)
                    continue;
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    private Product findProductById(int id) {
        for (Product product : productRepository.findAll()) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    private Product updateProductMembers(Product product, ProductPayload update) {
        product.setCategory(catagoryService.findCategoryByName(update.getCategoryName()));
        product.setName(update.getName());
        product.setPrice(Float.parseFloat(update.getPrice()));
        product.setMinPrice(Float.parseFloat(update.getMinPrice()));
        if (!update.getQuantity().equals("")) {
            product.setQuantity(product.getQuantity() + Integer.parseInt(update.getQuantity()));
        }
        product.setDiscription(update.getDescription());

        if(product.getPrice()<product.getMinPrice())
            throw new RegularLessThanMinimalPrice(product.getName());

        return product;
    }

    private Product createProductObject(ProductPayload productPayload) {
        Product product = new Product();
        product.setName(productPayload.getName());
        product.setQuantity(Integer.parseInt(productPayload.getQuantity()));
        product.setPrice(Float.parseFloat(productPayload.getPrice()));
        product.setMinPrice(Float.parseFloat(productPayload.getMinPrice()));
        product.setCategory(catagoryService.findCategoryByName(productPayload.getCategoryName()));
        product.setDiscription(productPayload.getDescription());

        if(product.getPrice()<product.getMinPrice())
            throw new RegularLessThanMinimalPrice(product.getName());

        return product;
    }

    private Product findProductOnSale(Product product) {
        for (Product p : findAllProductsOnSale("null")) {
            if (p.getProductId() == product.getProductId()) {
                return p;
            }
        }
        return null;
    }
}

