package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Product;
import sapproject.project.payload.ProductPayload;
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
    ProductService productService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        //log.debug("REST request to get all Products.");
        return productService.findAll();
    }

    @PostMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@Valid @RequestBody String id){
        //log.debug("REST request to get Product : {}", id);
        System.out.println("id of product: " + id);

        return  productService.getOne(Integer.parseInt(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody ProductPayload product) {
        //log.debug("REST request to save Product : {}", product);

        return productService.createOne(product);
    }

    @PostMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@Valid @RequestBody ProductPayload product){
        //log.debug("REST request to update Product : {}", ID);
        return productService.updateByID(product);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@Valid @RequestBody ProductPayload product) {
        //log.debug("REST request to delete Product : {}", ID);
        productService.deleteByID(Integer.parseInt(product.getId()));
    }
    @PostMapping("/filteredByCategory")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> filterByCategory(@Valid @RequestBody String category) {
        //log.debug("REST request to save Product : {}", product);
        return productService.findAllProductsByCategory(category);
    }
}
