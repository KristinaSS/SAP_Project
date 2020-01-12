package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.Order;
import sapproject.project.payload.Checkout;
import sapproject.project.services.interfaces.IOrderService;

import javax.validation.Valid;

@RequestMapping("order")
@RestController
public class OrderController {
    @Autowired
    private IOrderService orderServiceClass;

    @PostMapping("/makeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public Order makeOrder(@Valid @RequestBody Checkout checkout) {
        return orderServiceClass.makeOrder(checkout);
    }
}
