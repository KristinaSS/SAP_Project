package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.models.Category;
import sapproject.project.models.Product;
import sapproject.project.payload.ProductPayload;
import sapproject.project.repository.ProductRepository;
import sapproject.project.services.interfaces.IProductService;

import javax.validation.Payload;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CatagoryService catagoryService;

    public List<Product> findAllProductsByCategory(String categoryName){
        Category category = catagoryService.findCategoryByName(categoryName);
        List<Product> filteredList = new ArrayList<>();
        int catID = 0;
        if(category == null)
            return filteredList;//todo fix
        else
            catID = category.getCategoryId();
        for(Product product: productRepository.findAll()){
            if(product.getCategory().getCategoryId()==catID)
                filteredList.add(product);
        }
        return filteredList;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getOne(int Id) {
        return productRepository.findById(Id) .orElseGet(()->{
            try {
                throw new EntityNotFoundException(Product.class);
            } catch (EntityNotFoundException e) {
               // log.warn("A product with this Id has not been found:  {}", Id);
            }
            return null;
        });
    }


    @Override
    @Deprecated
    public Product createOne(Product entity) {
        return null;
    }

    public Product createOne(ProductPayload entity) {
        //log.info("New product has been created: {}", entity);
        Product product = createProductObject(entity);
        return productRepository.save(product);
    }

    private Product createProductObject(ProductPayload productPayload){
        Product product = new Product();
        product.setName(productPayload.getName());
        product.setQuantity(Integer.parseInt(productPayload.getQuantity()));
        product.setPrice(Float.parseFloat(productPayload.getPrice()));
        product.setCategory(catagoryService.findCategoryByName(productPayload.getCategoryName()));

        return product;
    }

    @Override
    public void deleteByID(int ID) {
        Product catagory = getOne(ID);
        if(catagory == null) {
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

    @Deprecated
    @Override
    public Product updateByID(int ID, Product entity) {
        return null;
    }


    public Product updateByID(ProductPayload payload) {
        Product product = findProductById(Integer.parseInt(payload.getId()));
        assert product != null;
        Product result = updateProductMembers(product, payload);

        return productRepository.save(result);
    }

    private Product findProductById (int id) {
        for (Product product: productRepository.findAll() ){
            if( product.getProductId() == id)
                return product;
        }
        return null;
    }

    private Product updateProductMembers(Product product, ProductPayload update){
        product.setCategory(catagoryService.findCategoryByName(update.getCategoryName()));
        product.setName(update.getName());
        product.setPrice(Float.parseFloat(update.getPrice()));
        product.setQuantity(product.getQuantity() + Integer.parseInt(update.getPrice()));

        return product;
    }
}
