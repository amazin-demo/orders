package amazin.orders.web;

import amazin.kernel.order.dto.OrderDto;
import amazin.orders.domain.repository.OrderRepository;
import amazin.orders.domain.mapping.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/orders")
@RestController
public class OrdersWebService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrdersWebService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") String id) {
        return orderRepository.findById(id)
                .map(OrderMapper.INSTANCE::orderToDto)
                .map(dto -> ok().body(dto))
                .orElse(notFound().build());
    }
}
