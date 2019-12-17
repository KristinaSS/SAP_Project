package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;
import sapproject.project.models.Order;
import sapproject.project.repository.OrderRepository;
import sapproject.project.services.interfaces.IOrderService;

import java.util.List;

@Log4j2
@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOne(int Id) {
        return orderRepository.findById(Id) .orElseGet(()->{
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
        if(catagory == null) {
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
                .map(accountType -> orderRepository.save(updateOrderMembers(accountType,entity)))
                .orElseGet(()->{
                    entity.setOrderId(ID);
                   // log.info("Order has been created: {}",ID);
                    return orderRepository.save(entity);
                });
    }

    private Order updateOrderMembers(Order order, Order update){
        order.setClient(update.getClient());
        order.setDateTime(update.getDateTime());
        //log.info("Order updated: {}", order);
        return order;
    }
}
