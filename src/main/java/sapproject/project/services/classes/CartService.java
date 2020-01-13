package sapproject.project.services.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.ListSizeIsZero;
import sapproject.project.models.*;
import sapproject.project.payload.CartItem;
import sapproject.project.repository.CartProductsRepository;
import sapproject.project.repository.CartRepository;
import sapproject.project.services.interfaces.ICartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
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

    @Override
    public Cart findCart(int accId) {
        for (Cart cart : cartRepository.findAll()) {
            if (cart.getAccount().getAccID() == accId)
                return cart;
        }
        return null;
    }

    @Override
    public boolean ifCartItemExists(Product product, Cart cart) {
        for (CartProducts cartProducts : cartProductsRepository.findAll()) {
            if (cartProducts.getCart().getCartId() == cart.getCartId()
                    && cartProducts.getProduct().getProductId() == product.getProductId())
                return true;
        }
        return false;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<CartProducts> filterByCategory(String username) {
        int accId = accountService.findAccountByEmail(username).getAccID();
        int cartId = 0;
        for (Cart cart : cartRepository.findAll()) {
            if (cart.getAccount().getAccID() == accId)
                cartId = cart.getCartId();
        }
        Product product;
        ProductCampaigns productCampaigns;
        List<CartProducts> filteredList = new ArrayList<>();

        for (CartProducts cartProducts : cartProductsRepository.findAll()) {
            if (cartId == cartProducts.getCart().getCartId()) {
                product = cartProducts.getProduct();
                productCampaigns = campaignService.findProductIfOnSale(product.getProductId());
                if (productCampaigns != null) {
                    product.setPrice(productCampaigns.getPrice());
                }
                cartProducts.setProduct(product);
                filteredList.add(cartProducts);
            }
        }
        if(filteredList.size()== 0)
            throw new ListSizeIsZero("filteredList");
        return filteredList;
    }

    @Override
    public CartProducts addItemToCart(CartItem cartItem) {
        Account account = accountService.findAccountByEmail(cartItem.getUsername(), true);
        Product product = productService.getOne(Integer.parseInt(cartItem.getProductId()));

        int quantity = Integer.parseInt(cartItem.getQuantity());

        Cart cart = cartRepository.findByAccount(account);
        CartProducts cartProducts = new CartProducts(quantity, cart, product);

        return ifCartItemExists(product, cart)
                ? cartProductsRepository.save(updateCartItem(cart, product, quantity))
                : cartProductsRepository.save(cartProducts);
    }
}
