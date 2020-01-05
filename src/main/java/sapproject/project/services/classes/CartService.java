package sapproject.project.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.models.Account;
import sapproject.project.models.Cart;
import sapproject.project.models.CartProducts;
import sapproject.project.models.Product;
import sapproject.project.payload.CartItem;
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
    public void deleteCartItem (CartItem cartItem) {
        Account account = accountService.findAccountByEmail(cartItem.getUsername(), true);
        Cart cart = cartRepository.findByAccount(account);

        for (CartProducts item: cartProductsRepository.findAll()){
            if(item.getCart().getCartId() == cart.getCartId()
                    && item.getProduct().getProductId() == Integer.parseInt(cartItem.getProductId())){
                cartProductsRepository.delete(item);
            }
        }

    }
}
