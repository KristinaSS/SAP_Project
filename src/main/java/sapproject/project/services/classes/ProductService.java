package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.models.Product;
import sapproject.project.repository.ProductRepository;
import sapproject.project.services.interfaces.IProductService;

import java.util.List;

@Log4j2
@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

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
    public Product createOne(Product entity) {
        //log.info("New product has been created: {}", entity);
        return productRepository.save(entity);
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

    @Override
    public Product updateByID(int ID, Product entity) {
        return productRepository.findById(ID)
                .map(accountType -> productRepository.save(updateProductMembers(accountType,entity)))
                .orElseGet(()->{
                    entity.setProductId(ID);
                   // log.info("Order has been created: {}",ID);
                    return productRepository.save(entity);
                });
    }

    private Product updateProductMembers(Product product, Product update){
        product.setCatagory(update.getCatagory());
        product.setName(update.getName());
        product.setPrice(update.getPrice());
        product.setQuantity(update.getQuantity());
        //log.info("Order updated: {}", product);
        return product;
    }
}
