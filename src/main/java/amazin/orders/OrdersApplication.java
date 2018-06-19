package amazin.orders;

import amazin.orders.domain.entity.Order;
import amazin.orders.domain.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner dataLoader(OrderRepository orderRepository) {
        return args -> {
            final Order order = new Order();
            log.info("Creating order {}...", order.getId());
            orderRepository.save(order);
        };
    }
}
