package sapproject.project.services.classes;

import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapproject.project.exceptions.EntityNotFoundException;

import sapproject.project.models.*;
import sapproject.project.payload.ReportPayload;
import sapproject.project.repository.OrderDetailsRepository;
import sapproject.project.services.interfaces.IOrderDetailsService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class OrderDetailsService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    private CampaignService campaignService;

    private static float sum;
    private static int quantity;


    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    public List<ReportPayload> getSalesReport(String time) {
        List<ReportPayload> reportPayloadList = new ArrayList<>();
        List<Product> reportedProducts = new ArrayList<>();

        //DateTimeFormatter format = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime;

        for (OrderDetails orderDetails : findAll()) {
            Product product = productService.getOne(orderDetails.getOrderdetailsPK().getProductId());
            localDateTime = getDateTimeOfOrder(orderDetails.getOrderdetailsPK().getOrderId());
            if (!isInTimeRequested(time, localDateTime))
                continue;

            sum = 0;
            quantity = 0;
            intializeSumAndQuantity(reportedProducts, product);
            addToPayloadList(reportedProducts, product, reportPayloadList);
        }
        return reportPayloadList;
    }

    private LocalDateTime getDateTimeOfOrder(int orderId) {
        Order order = orderService.getOne(orderId);
        String dateTime = order.getDateTime();
        return LocalDateTime.parse(dateTime);
    }

    private boolean isInTimeRequested(String time, LocalDateTime localDateTime) {
        switch (time.trim().toLowerCase()) {
            case "year": {
                if (localDateTime.getYear() == LocalDateTime.now().getYear()) {
                    return true;
                }
            }
            case "month": {
                if (localDateTime.getMonth() == LocalDateTime.now().getMonth())
                    return true;
            }
            case "day": {
                if (localDateTime.getDayOfYear() == LocalDateTime.now().getDayOfYear())
                    return true;
            }
            default:
                break;
        }
        return false;
    }

    private void intializeSumAndQuantity(List<Product> reportedProducts, Product product) {
        for (OrderDetails od : findAll()) {
            if (reportedProducts.contains(product))
                continue;
            if (od.getOrderdetailsPK().getProductId() == product.getProductId()) {
                sum += od.getSum();
                quantity += od.getQuantity();
            }
        }
    }

    private void addToPayloadList(List<Product> reportedProducts, Product product, List<ReportPayload> reportPayloadList) {
        if (!reportedProducts.contains(product)) {
            reportedProducts.add(product);

            ReportPayload payload = new ReportPayload();
            payload.setProductName(product.getName());
            payload.setQuantity(quantity);
            payload.setSum(sum);
            payload.setProductId(product.getProductId());
            reportPayloadList.add(payload);
        }
    }

    public BigDecimal calculate(String time) {
        float total = 0;
        LocalDateTime localDateTime;

        for (OrderDetails orderDetails : findAll()) {
            localDateTime = getDateTimeOfOrder(orderDetails.getOrderdetailsPK().getOrderId());
            if (!isInTimeRequested(time, localDateTime))
                continue;
            total += orderDetails.getSum();
        }
        return round(total,2);
    }
    private BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
}
