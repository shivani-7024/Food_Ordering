package shivani.food_odering.Service;

import shivani.food_odering.Model.Order;
import shivani.food_odering.Model.User;
import shivani.food_odering.Request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus)throws Exception;

    public void cancelOrder(Long orderId)throws Exception;

    public List<Order> getUserOrders(Long userId)throws Exception;

    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus)throws Exception;

    public Order findOrderById(Long orderId)throws Exception;
}
