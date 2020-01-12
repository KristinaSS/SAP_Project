package sapproject.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapproject.project.payload.ReportPayload;
import sapproject.project.services.interfaces.IOrderDetailsService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    @Autowired
    private IOrderDetailsService orderDetailsService;

    @PostMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public List<ReportPayload> createSalesReport(@Valid @RequestBody String time) {
        return orderDetailsService.getSalesReport(time);
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculateReport(@Valid @RequestBody String time) {
        return orderDetailsService.calculate(time);
    }

}
