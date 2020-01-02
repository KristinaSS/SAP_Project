package sapproject.project.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.models.Account;
import sapproject.project.models.Cart;
import sapproject.project.models.Product;
import sapproject.project.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    public Cart createCart(String username){
        Account account = accountService.findAccountByEmail(username);
        Cart cart = new Cart(account);

        return cartRepository.save(cart);
    }

    public void addItemToCart (int productId){
        Product product = productService.getOne(productId);
    }
}
