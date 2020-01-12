package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Account;
import sapproject.project.models.Cart;
import sapproject.project.models.CartProducts;
import sapproject.project.payload.CartItem;
import sapproject.project.repository.CartRepository;
import sapproject.project.services.interfaces.IAccountService;
import sapproject.project.services.interfaces.ICartService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ICartService cartService;


    private static final float SHIPPING_PRICE = 5f;


    @PostMapping("/getAllByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<CartProducts> filterByCategory(@Valid @RequestBody String username) {
        return cartService.filterByCategory(username);
    }

    @PostMapping("/addItem")
    @ResponseStatus(HttpStatus.OK)
    public CartProducts addItemToCart(@Valid @RequestBody CartItem cartItem) {
        return cartService.addItemToCart(cartItem);
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public Float calculate(@Valid @RequestBody String username) {
        Account account = accountService.findAccountByEmail(username, true);
        Cart cart = cartRepository.findByAccount(account);
        return cartService.calculateCart(cart);
    }

    @PostMapping("/calculate-with-shipping")
    @ResponseStatus(HttpStatus.OK)
    public Float calculateWithShipping(@Valid @RequestBody String username) {
        Account account = accountService.findAccountByEmail(username, true);
        Cart cart = cartRepository.findByAccount(account);
        return cartService.calculateCart(cart) + SHIPPING_PRICE;
    }

    @PostMapping("/deleteItem")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@Valid @RequestBody CartItem cartItem) {
        cartService.deleteCartItem(cartItem);
    }

}
