package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Order;
import sapproject.project.payload.Checkout;
import sapproject.project.services.classes.OrderService;
import sapproject.project.services.interfaces.IOrderService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RequestMapping("order/")
@RestController
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    OrderService orderServiceClass;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllAccountTypes() {
        //log.debug("REST request to get all Order Details.");
        return orderService.findAll();
    }

    @GetMapping("/get-{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable(name = "id") int id){
        //log.debug("REST request to get Order : {}", id);
        return  orderService.getOne(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody Order order) {
        //log.debug("REST request to save Order : {}", order);
        return orderService.createOne(order);
    }

    @PutMapping("/edit-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrder(@PathVariable(value = "ID") Integer ID,
                                         @Valid @RequestBody Order order){
        //log.debug("REST request to update Order : {}", ID);
        return orderService.updateByID(ID,order);
    }

    @DeleteMapping("/delete-{ID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable(value = "ID") Integer ID) {
       // log.debug("REST request to delete Order : {}", ID);
        orderService.deleteByID(ID);
    }

    @PostMapping("/makeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public Order makeOrder(@Valid @RequestBody Checkout checkout) {
        //log.debug("REST request to save Order : {}", order);
        return orderServiceClass.makeOrder(checkout);
    }
}
