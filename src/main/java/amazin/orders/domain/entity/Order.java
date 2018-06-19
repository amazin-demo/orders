package amazin.orders.domain.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    private final String id = UUID.randomUUID().toString();

    private String accountId;

    @OneToMany
    private List<OrderItem> items;

    public int calculateTotalPrice() {
        return items.stream().mapToInt(OrderItem::getPrice).sum();
    }
}
