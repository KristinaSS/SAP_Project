package sapproject.project.services.interfaces;

import sapproject.project.models.Order;
import sapproject.project.payload.Checkout;

import java.util.List;

public interface IOrderService{
    List<Order> findAll();

    Order getOne(int Id);

    Order makeOrder(Checkout checkout);
}
