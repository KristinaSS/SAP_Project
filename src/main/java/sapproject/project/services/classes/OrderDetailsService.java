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
public class OrderDetailsService{
    @Autowired
    OrderDetailsRepository orderDetailsRepository;


    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

}
