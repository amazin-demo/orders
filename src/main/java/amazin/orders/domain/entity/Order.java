package amazin.orders.domain.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Value;

@Entity
@Value
@Table(name="orders")
public class Order {
    @Id
    private final String id = UUID.randomUUID().toString();

}
