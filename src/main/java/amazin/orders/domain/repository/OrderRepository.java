package amazin.orders.domain.repository;

import amazin.orders.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,String> {

}
