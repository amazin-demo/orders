package amazin.orders.domain.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class OrderItem {

    @Id
    private final String id = UUID.randomUUID().toString();

    private String sku;
    private int quantity;
    private int price;
}
