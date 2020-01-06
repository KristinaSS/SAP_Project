package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.OrderDetails;
import sapproject.project.payload.ReportPayload;
import sapproject.project.services.classes.OrderDetailsService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    @Autowired
    OrderDetailsService orderDetailsService;

    @GetMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public List<ReportPayload> createSalesReport(){
        return orderDetailsService.getSalesReport();
    }

    @GetMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public Float calculateReport(){
        return orderDetailsService.calculate();
    }

}
