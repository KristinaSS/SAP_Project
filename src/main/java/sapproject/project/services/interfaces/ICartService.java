package sapproject.project.services.interfaces;

import sapproject.project.models.Cart;
import sapproject.project.models.CartProducts;
import sapproject.project.models.Product;
import sapproject.project.payload.CartItem;

import java.util.List;

public interface ICartService {
    Cart findCart(int accId);

    boolean ifCartItemExists(Product product, Cart cart);

    CartProducts updateCartItem(Cart cart, Product product, int quantity);

    Float calculateCart(Cart cart);

    void deleteCartItem(CartItem cartItem);

    List<CartProducts> filterByCategory(String username);

    CartProducts addItemToCart(CartItem cartItem);
}
