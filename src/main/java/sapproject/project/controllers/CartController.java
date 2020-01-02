package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Account;
import sapproject.project.models.Cart;
import sapproject.project.models.CartProducts;
import sapproject.project.models.Product;
import sapproject.project.payload.CartItem;
import sapproject.project.repository.CartProductsRepository;
import sapproject.project.repository.CartRepository;
import sapproject.project.services.classes.AccountService;
import sapproject.project.services.classes.CartService;
import sapproject.project.services.classes.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @Autowired
    CartProductsRepository cartProductsRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartService cartService;

    @PostMapping("/getAllByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<CartProducts> filterByCategory(@Valid @RequestBody String username) {
        int accId = accountService.findAccountByEmail(username).getAccID();
        int cartId = 0;
        for (Cart cart : cartRepository.findAll()) {
            if (cart.getAccount().getAccID() == accId)
                cartId = cart.getCartId();
        }
        List<CartProducts> filteredList = new ArrayList<>();
        for (CartProducts cartProducts : cartProductsRepository.findAll()) {
            if (cartId == cartProducts.getCart().getCartId()) {
                filteredList.add(cartProducts);
            }
        }
        return filteredList;
    }

    @PostMapping("/addItem")
    @ResponseStatus(HttpStatus.OK)
    public CartProducts addItemToCart(@Valid @RequestBody CartItem cartItem) {
        Account account = accountService.findAccountByEmail(cartItem.getUsername(), true);
        Product product = productService.getOne(Integer.parseInt(cartItem.getProductId()));
        int quantity = Integer.parseInt(cartItem.getQuantity());
        Cart cart = cartRepository.findByAccount(account);
        CartProducts cartProducts = new CartProducts(quantity, cart, product);

        return cartService.ifCartItemExists(product, cart)
                ? cartProductsRepository.save(cartService.updateCartItem(cart, product, quantity))
                : cartProductsRepository.save(cartProducts);
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public Float calculate(@Valid @RequestBody String username) {
        Account account = accountService.findAccountByEmail(username, true);
        Cart cart = cartRepository.findByAccount(account);
        return cartService.calculateCart(cart);
    }
}
