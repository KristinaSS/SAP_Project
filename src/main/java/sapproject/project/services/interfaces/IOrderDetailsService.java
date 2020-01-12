package sapproject.project.services.interfaces;

import sapproject.project.models.OrderDetails;
import sapproject.project.payload.ReportPayload;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderDetailsService {

    List<OrderDetails> findAll();

    List<ReportPayload> getSalesReport(String time);

    BigDecimal calculate(String time);
}
