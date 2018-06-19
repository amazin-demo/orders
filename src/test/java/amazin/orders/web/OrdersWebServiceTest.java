package amazin.orders.web;

import java.util.Optional;

import amazin.orders.client.payment.PaymentsClient;
import amazin.orders.domain.entity.Order;
import amazin.orders.domain.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OrdersWebServiceTest {
    private MockMvc mockMvc;

    @Mock
    private OrderRepository orderRepositoryMock;

    @Mock
    private PaymentsClient paymentsClientMock;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(new OrdersWebService(orderRepositoryMock, paymentsClientMock))
                .build();
    }

    @Test
    public void getOrder() throws Exception {
        final Order order = new Order();
        when(orderRepositoryMock.findById("12345")).thenReturn(Optional.of(order));

        mockMvc.perform(get("/orders/12345"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(order.getId())));
    }

}