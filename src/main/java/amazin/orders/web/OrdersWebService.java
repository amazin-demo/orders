package amazin.orders.web;

import amazin.kernel.order.command.PlaceOrder;
import amazin.kernel.order.dto.OrderDto;
import amazin.kernel.payment.command.ProcessPayment;
import amazin.orders.client.payment.PaymentsClient;
import amazin.orders.domain.entity.Order;
import amazin.orders.domain.mapping.OrderMapper;
import amazin.orders.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/orders")
@RestController
public class OrdersWebService {

    private final OrderRepository orderRepository;
    private final PaymentsClient paymentsClient;

    @Autowired
    public OrdersWebService(OrderRepository orderRepository, PaymentsClient paymentsClient) {
        this.orderRepository = orderRepository;
        this.paymentsClient = paymentsClient;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") String id) {
        return orderRepository.findById(id)
                .map(OrderMapper.INSTANCE::orderToDto)
                .map(dto -> ok().body(dto))
                .orElse(notFound().build());
    }

    @PostMapping(path = "")
    public OrderDto placeOrder(@RequestBody PlaceOrder placeOrder) {
        final Order order = OrderMapper.INSTANCE.placeOrderToOrder(placeOrder);
        orderRepository.save(order);
        paymentsClient.processPayment(new ProcessPayment(placeOrder.getAccountId(), order.calculateTotalPrice()));
        return OrderMapper.INSTANCE.orderToDto(order);
    }
}
