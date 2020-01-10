package sapproject.project.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.models.*;
import sapproject.project.payload.CartItem;
import sapproject.project.repository.CartProductsRepository;
import sapproject.project.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private CartProductsRepository cartProductsRepository;

    @Autowired
    private CampaignService campaignService;

    public Cart findCart(int accId) {
        for (Cart cart : cartRepository.findAll()) {
            if (cart.getAccount().getAccID() == accId)
                return cart;
        }
        return null;
    }

    public boolean ifCartItemExists(Product product, Cart cart) {
        for (CartProducts cartProducts : cartProductsRepository.findAll()) {
            if (cartProducts.getCart().getCartId() == cart.getCartId()
                    && cartProducts.getProduct().getProductId() == product.getProductId())
                return true;
        }
        return false;
    }

    public CartProducts updateCartItem(Cart cart, Product product, int quantity) {
        CartProducts item = null;
        for (CartProducts cartProducts : cartProductsRepository.findAll()) {
            if (cartProducts.getCart().getCartId() == cart.getCartId()
                    && cartProducts.getProduct().getProductId() == product.getProductId())
                item = cartProducts;
        }
        if (item != null)
            item.setQuantity(item.getQuantity() + quantity);
        return item;
    }

    public Float calculateCart(Cart cart) {
        float sum = 0;

        Product product;
        for (CartProducts cartProducts : cartProductsRepository.findAll()) {
            if (cartProducts.getCart().getCartId() == cart.getCartId()) {
                ProductCampaigns productCampaigns;
                product = cartProducts.getProduct();
                productCampaigns = campaignService.findProductIfOnSale(product.getProductId());
                if (productCampaigns != null) {
                    product.setPrice(productCampaigns.getPrice());
                }
                sum += (cartProducts.getQuantity() * product.getPrice());
            }
        }
        System.out.println("total sum: " + sum);
        return sum;
    }

    public void deleteCartItem(CartItem cartItem) {
        Account account = accountService.findAccountByEmail(cartItem.getUsername(), true);
        Cart cart = cartRepository.findByAccount(account);

        for (CartProducts item : cartProductsRepository.findAll()) {
            if (item.getCart().getCartId() == cart.getCartId()
                    && item.getProduct().getProductId() == Integer.parseInt(cartItem.getProductId())) {
                cartProductsRepository.delete(item);
            }
        }

    }
}
