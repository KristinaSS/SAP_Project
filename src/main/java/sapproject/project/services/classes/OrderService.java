package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.exceptions.ListSizeIsZero;
import sapproject.project.exceptions.NotEnoughProductsException;
import sapproject.project.models.*;
import sapproject.project.payload.Checkout;
import sapproject.project.repository.*;
import sapproject.project.services.interfaces.IOrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartProductsRepository cartProductsRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CampaignService campaignService;

    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        if (orders.size() == 0)
            throw new ListSizeIsZero("orders");
        return orders;
    }

    @Override
    public Order getOne(int Id) {
        return orderRepository.findById(Id).orElseGet(() -> {
            try {
                throw new EntityNotFoundException(Order.class);
            } catch (EntityNotFoundException e) {
            }
            return null;
        });
    }

    @Override
    public Order makeOrder(Checkout checkout) {
        Account account = accountService.findAccountByEmail(checkout.getUsername(), true);

        if(!isOrderPossible(account))
            throw new NotEnoughProductsException();

        String dateTime = LocalDateTime.now().toString();
        String address = createAddress(checkout);
        String paymentDetails = createPaymentDetails(checkout);
        String phoneNumber = checkout.getPhoneNumber();

        Order order = new Order(dateTime, account, address, paymentDetails, phoneNumber);
        Order createdOrder = orderRepository.save(order);

        int createdorderId = createdOrder.getOrderId();
        List<OrderDetails> orderDetailsList = getItemsToBuy(createdorderId, checkout, account);
        addToOrderDetailsRepository(orderDetailsList);

        return order;
    }

    private String createAddress(Checkout checkout) {

        return checkout.getCountry() + ", " +
                checkout.getAddress() + ", " +
                checkout.getCity() + ", " +
                checkout.getState() + ", " +
                checkout.getPostCode();
    }

    private String createPaymentDetails(Checkout checkout) {
        return "Card Type: " + checkout.getCardType() +
                "\nCard Number: " + checkout.getCardNumber() +
                "\nCard CVV: " + checkout.getCVV() +
                "\nExpiration Date: " + checkout.getExpirationMonth() + " " + checkout.getExpirationYear();
    }

    private boolean isOrderPossible(Account account){
        Cart cart = cartRepository.findByAccount(account);
        Product product;

        for (CartProducts item : cartProductsRepository.findAll()) {
            if (item.getCart().getCartId() == cart.getCartId()) {
                product = productService.getOne(item.getProduct().getProductId());
                product.setQuantity(item.getProduct().getQuantity() - item.getQuantity());
                if (product.getQuantity()< 0)
                    return false;
            }
        }
        return true;
    }

    private List<OrderDetails> getItemsToBuy(int orderId, Checkout checkout, Account account) {
        Cart cart = cartRepository.findByAccount(account);
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        OrderDetailsPK pk;
        Product product;
        float price;
        ProductCampaigns productCampaigns;

        for (CartProducts item : cartProductsRepository.findAll()) {
            if (item.getCart().getCartId() == cart.getCartId()) {
                product = productService.getOne(item.getProduct().getProductId());
                product.setQuantity(item.getProduct().getQuantity() - item.getQuantity());
                productRepository.save(product);


                productCampaigns = campaignService.findProductIfOnSale(product.getProductId());
                if (productCampaigns != null) {
                    price = productCampaigns.getPrice();
                } else {
                    price = product.getPrice();
                }

                pk = new OrderDetailsPK(orderId, item.getProduct().getProductId());
                orderDetailsList.add(new OrderDetails(pk,
                        price,
                        item.getQuantity(),
                        (item.getQuantity() * price)));
            }
        }
        deleteAllByCartId(cart.getCartId());
        return orderDetailsList;
    }

    private void addToOrderDetailsRepository(List<OrderDetails> orderDetailsList) {
        for (OrderDetails orderDetails : orderDetailsList) {
            orderDetailsRepository.save(orderDetails);
        }

    }

    private void deleteAllByCartId(int cartId) {
        Iterator<CartProducts> iterator = cartProductsRepository.findAll().iterator();
        CartProducts cartProducts;
        while (iterator.hasNext()) {
            cartProducts = iterator.next();

            if (cartProducts.getCart().getCartId() == cartId)
                cartProductsRepository.delete(cartProducts);
        }
    }
}
