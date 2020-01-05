package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
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
    OrderRepository orderRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartProductsRepository cartProductsRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOne(int Id) {
        return orderRepository.findById(Id).orElseGet(() -> {
            try {
                throw new EntityNotFoundException(Order.class);
            } catch (EntityNotFoundException e) {
                //log.warn("A order with this Id has not been found:  {}", Id);
            }
            return null;
        });
    }

    @Override
    public Order createOne(Order entity) {
        //log.info("New order has been created: {}", entity);
        return orderRepository.save(entity);
    }

    @Override
    public void deleteByID(int ID) {
        Order catagory = getOne(ID);
        if (catagory == null) {
            try {
                throw new EntityNotFoundException(Order.class);
            } catch (EntityNotFoundException e) {
                // log.warn("Order not found: {}", ID);
            }
            return;
        }
        //log.info("Deleted order: {} ",ID);
        orderRepository.delete(catagory);

    }

    @Override
    public Order updateByID(int ID, Order entity) {
        return orderRepository.findById(ID)
                .map(accountType -> orderRepository.save(updateOrderMembers(accountType, entity)))
                .orElseGet(() -> {
                    entity.setOrderId(ID);
                    // log.info("Order has been created: {}",ID);
                    return orderRepository.save(entity);
                });
    }

    private Order updateOrderMembers(Order order, Order update) {
        order.setClient(update.getClient());
        order.setDateTime(update.getDateTime());
        //log.info("Order updated: {}", order);
        return order;
    }

    public Order makeOrder(Checkout checkout) {
        Account account = accountService.findAccountByEmail(checkout.getUsername(), true);
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

    private String createAddress(Checkout checkout){

        return  checkout.getCountry() + ", " +
                checkout.getAddress() + ", " +
                checkout.getCity() + ", " +
                checkout.getState() + ", " +
                checkout.getPostCode();
    }

    private String createPaymentDetails(Checkout checkout){
        return "Card Type: " + checkout.getCardType() +
                "\nCard Number: " + checkout.getCardNumber() +
                "\nCard CVV: " + checkout.getCVV() +
                "\nExpiration Date: " + checkout.getExpirationMonth()+ " " + checkout.getExpirationYear();
    }

    private List<OrderDetails> getItemsToBuy(int orderId, Checkout checkout, Account account){
        Cart cart = cartRepository.findByAccount(account);
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        OrderDetailsPK pk;

        for (CartProducts item : cartProductsRepository.findAll()) {
            if (item.getCart().getCartId() == cart.getCartId()) {
                item.getProduct().setQuantity(item.getProduct().getQuantity()-item.getQuantity());
                productRepository.save(item.getProduct());

                pk = new OrderDetailsPK(orderId,item.getProduct().getProductId());
                orderDetailsList.add(new OrderDetails(pk,
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        (item.getQuantity()*item.getProduct().getPrice())));
            }
        }
        deleteAllByCartId(cart.getCartId());
        return orderDetailsList;
    }

    private void addToOrderDetailsRepository(List<OrderDetails> orderDetailsList){
        for(OrderDetails orderDetails: orderDetailsList){
            orderDetailsRepository.save(orderDetails);
        }

    }
    private void deleteAllByCartId(int cartId){
        Iterator<CartProducts> iterator = cartProductsRepository.findAll().iterator();
        CartProducts cartProducts;
        while (iterator.hasNext()){
            cartProducts = iterator.next();

            if(cartProducts.getCart().getCartId() == cartId)
                cartProductsRepository.delete(cartProducts);
        }
    }
}
