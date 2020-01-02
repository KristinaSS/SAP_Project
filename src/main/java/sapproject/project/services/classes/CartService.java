package sapproject.project.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.models.Account;
import sapproject.project.models.Cart;
import sapproject.project.models.CartProducts;
import sapproject.project.models.Product;
import sapproject.project.repository.CartProductsRepository;
import sapproject.project.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartProductsRepository cartProductsRepository;

    public Cart createCart(String username){
        Account account = accountService.findAccountByEmail(username);
        Cart cart = new Cart(account);

        return cartRepository.save(cart);
    }

    public void addItemToCart (int productId){
        Product product = productService.getOne(productId);
    }

    public boolean ifCartItemExists(Product product, Cart cart){
        for (CartProducts cartProducts: cartProductsRepository.findAll()){
            if(cartProducts.getCart().getCartId() == cart.getCartId()
                    && cartProducts.getProduct().getProductId() == product.getProductId())
                return true;
        }
        return false;
    }

    public CartProducts updateCartItem(Cart cart, Product product, int quantity){
        CartProducts item = null;
        for (CartProducts cartProducts: cartProductsRepository.findAll()){
            if(cartProducts.getCart().getCartId() == cart.getCartId()
                    && cartProducts.getProduct().getProductId() == product.getProductId())
                item = cartProducts;
        }
        if(item != null)
            item.setQuantity(item.getQuantity()+quantity);
        return item;
    }
    public Float calculateCart(Cart cart) {
        float sum = 0;

        for (CartProducts cartProducts: cartProductsRepository.findAll()){
            if(cartProducts.getCart().getCartId() == cart.getCartId()){
                sum += (cartProducts.getQuantity() * cartProducts.getProduct().getPrice());
            }
        }
        System.out.println("total sum: "+ sum);
        return sum;
    }
}
