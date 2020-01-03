package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;

import sapproject.project.models.OrderDetails;
import sapproject.project.models.OrderDetailsPK;
import sapproject.project.repository.OrderDetailsRepository;
import sapproject.project.services.interfaces.IOrderDetailsService;

import java.util.List;

@Log4j2
@Service
public class OrderDetailsService implements IOrderDetailsService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails createOne(OrderDetails entity) {
       // log.info("New order details has been created: {}", entity);
        return orderDetailsRepository.save(entity);
    }

    //todo be fixed
    @Deprecated
    @Override
    public OrderDetails updateByID(int ID, OrderDetails entity) {
        return null;
    }

    public OrderDetails updateByID(OrderDetailsPK ID, OrderDetails entity) {
        return orderDetailsRepository.findById(ID)
                .map(accountType -> orderDetailsRepository.save(updateOrderDetailMemebers(accountType,entity)))
                .orElseGet(()->{
                    //todo fix this
                    /*entity.setCategoryId(ID);*/
                   // log.info("New order details have been created: {}",ID);
                    return orderDetailsRepository.save(entity);
                });
    }

    @Deprecated
    @Override
    public OrderDetails getOne(int Id) {
        return null;
    }

    public OrderDetails getOne(OrderDetailsPK Id) {
        return orderDetailsRepository.findById(Id) .orElseGet(()->{
            try {
                throw new EntityNotFoundException(OrderDetails.class);
            } catch (EntityNotFoundException e) {
               // log.warn("Order details with this Id has not been found:  {}", Id);
            }
            return null;
        });
    }

    @Override
    public void deleteByID(int ID) {
        OrderDetails catagory = getOne(ID);
        if(catagory == null) {
            try {
                throw new EntityNotFoundException(OrderDetails.class);
            } catch (EntityNotFoundException e) {
               // log.warn("Order details not found: {}", ID);
            }
            return;
        }
       // log.info("Deleted order details: {} ",ID);
        orderDetailsRepository.delete(catagory);

    }

    private OrderDetails updateOrderDetailMemebers(OrderDetails orderDetails, OrderDetails updated){
        orderDetails.setQuantity(updated.getQuantity());
        //todo fix sum
        //log.info("Order Details updated: {}", orderDetails);
        return orderDetails;
    }
}
