package sapproject.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.models.OrderDetails;
import sapproject.project.payload.ReportPayload;
import sapproject.project.services.classes.OrderDetailsService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    @Autowired
    OrderDetailsService orderDetailsService;

    @PostMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public List<ReportPayload> createSalesReport(@Valid @RequestBody String time){
        return orderDetailsService.getSalesReport(time);
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculateReport(@Valid @RequestBody String time){
        return orderDetailsService.calculate(time);
    }

}
