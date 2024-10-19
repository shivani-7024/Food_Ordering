package shivani.food_odering.Service;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shivani.food_odering.Model.*;
import shivani.food_odering.Repository.*;
import shivani.food_odering.Request.OrderRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestuarentService restuarentService;
    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippAddress = order.getDeliveryAddress();
        Address saveAddress = addressRepository.save(shippAddress);
        if(!user.getAddresses().contains(saveAddress)) {
            user.getAddresses().add(saveAddress);
            userRepository.save(user);
        }

        Restaurent restaurent = restuarentService.getRestaurentById(order.getRestaurantId());
        Order createOrder = new Order();
        createOrder.setCustomer(user);
        createOrder.setCreatedAt(new Date());
        createOrder.setStatus("PENDING");
        createOrder.setAddress(saveAddress);
        createOrder.setRestaurent(restaurent);

        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItems cartItems : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItems.getFood());
            orderItem.setQuantity(cartItems.getQuantity());
            orderItem.setIngredients(cartItems.getIngredients());
            orderItem.setTotalPrice(cartItems.getPrice());
            OrderItem orderItemSaved = orderItemRepository.save(orderItem);
            orderItems.add(orderItemSaved);
        }
        Long total = cartService.calculateCartTotals(cart);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(total);

        Order orderSaved = orderRepository.save(createOrder);
        restaurent.getOrders().add(orderSaved);

        return createOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")) {
            order.setStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);

    }

    @Override
    public List<Order> getUserOrders(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null){
            orders = orders.stream().filter(order->order.getStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()){
            throw new Exception("Order not found");
        }
        return order.get();
    }
}
