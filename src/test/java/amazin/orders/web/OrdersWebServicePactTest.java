package amazin.orders.web;

import amazin.orders.client.payment.PaymentsClient;
import amazin.orders.domain.repository.OrderRepository;
import au.com.dius.pact.provider.junit.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(RestPactRunner.class)
@Provider("orders")
@IgnoreNoPactsToVerify
@PactBroker(host = "${pactbroker.host:localhost}", port = "${pactbroker.port:80}")
public class OrdersWebServicePactTest {

    @TestTarget
    public final MockMvcTarget target = new MockMvcTarget();

    @Mock
    private OrderRepository orderRepositoryMock;

    @Mock
    private PaymentsClient paymentsClientMock;

    @Before
    public void initTarget() {
        MockitoAnnotations.initMocks(this);
        target.setControllers(new OrdersWebService(orderRepositoryMock, paymentsClientMock));
    }


}
