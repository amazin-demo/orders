package amazin.orders.domain.mapping;

import amazin.kernel.order.command.PlaceOrder;
import amazin.kernel.order.dto.OrderDto;
import amazin.kernel.order.dto.OrderItemDto;
import amazin.orders.domain.entity.Order;
import amazin.orders.domain.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderToDto(Order order);

    OrderItem orderItemDtoToOrderItem(OrderItemDto dto);

    Order placeOrderToOrder(PlaceOrder command);
}
