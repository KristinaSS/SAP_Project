package sapproject.project.services.interfaces;

import sapproject.project.models.Product;
import sapproject.project.payload.ProductPayload;

import java.util.List;

public interface IProductService {
    List<Product> findAllProductsByCategory(String categoryName);

    List<Product> findAll();

    Product getOne(int Id);

    Product createOne(ProductPayload entity);

    void deleteByID(int ID);

    Product updateByID(ProductPayload payload);

    List<Product> findAllOutOfStockProducts();

    List<Product> findAllProductsByKeyword(String keyword);

    List<Product> findAllProductsOnSale(String campaignName);
}
