package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Cart;
import sapproject.project.models.CartProducts;
import sapproject.project.models.Product;
import sapproject.project.repository.CartProductsRepository;
import sapproject.project.repository.CartRepository;
import sapproject.project.services.classes.AccountService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    AccountService accountService;
    @Autowired
    CartProductsRepository cartProductsRepository;

    @Autowired
    CartRepository cartRepository;

    @PostMapping("/getAllByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<CartProducts> filterByCategory(@Valid @RequestBody String username) {
        int accId = accountService.findAccountByEmail(username).getAccID();
        int cartId= 0;
        for(Cart cart:cartRepository.findAll()){
            if(cart.getAccount().getAccID() == accId)
                cartId = cart.getCartId();
        }
        List<CartProducts> filteredList = new ArrayList<>();
        for(CartProducts cartProducts: cartProductsRepository.findAll()){
            if(cartId == cartProducts.getCart().getCartId()){
                filteredList.add(cartProducts);
            }
        }
       return filteredList;
    }

}
