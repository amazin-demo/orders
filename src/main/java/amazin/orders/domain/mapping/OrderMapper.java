package amazin.orders.domain.mapping;

import amazin.kernel.order.dto.OrderDto;
import amazin.orders.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderToDto(Order order);
}
