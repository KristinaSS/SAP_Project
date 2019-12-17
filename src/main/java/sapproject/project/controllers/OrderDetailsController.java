package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.OrderDetails;
import sapproject.project.services.interfaces.IOrderDetailsService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    @Autowired
    IOrderDetailsService orderDetailsService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDetails> getAllOrderDetails() {
        //log.debug("REST request to get all Order Details.");
        return orderDetailsService.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetails createOrderDetails(@Valid @RequestBody OrderDetails orderDetails) {
        //log.debug("REST request to save Order Details : {}", orderDetails);
        return orderDetailsService.createOne(orderDetails);
    }
    
    //to be fixed

    @GetMapping("/get-{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetails getOrderDetails(@PathVariable(name = "id") int id){
        //log.debug("REST request to get Order Details : {}", id);
        return  orderDetailsService.getOne(id);
    }

    @PutMapping("/edit-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetails updateOrderDetails(@PathVariable(value = "ID") Integer ID,
                                           @Valid @RequestBody OrderDetails orderDetails){
       // log.debug("REST request to update Order Details : {}", ID);
        return orderDetailsService.updateByID(ID,orderDetails);
    }

    @DeleteMapping("/delete-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderDetails(@PathVariable(value = "ID") Integer ID) {
        //log.debug("REST request to delete Order Details : {}", ID);
        orderDetailsService.deleteByID(ID);
    }
}
