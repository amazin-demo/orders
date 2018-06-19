package amazin.orders.client.payment;

import amazin.kernel.payment.command.ProcessPayment;
import amazin.kernel.payment.dto.PaymentDto;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DefaultPaymentsClientTest {
    @Rule
    public final PactProviderRuleMk2 pactRule = new PactProviderRuleMk2("payments", this);

    @Pact(provider = "payments", consumer = "orders")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .uponReceiving("Process Payment")
                .path("/payments")
                .method("POST")
                .body(newJsonBody(o ->
                        o.stringValue("accountId", "12345")
                                .numberValue("amount", 100)
                ).build())
                .willRespondWith()
                .status(200)
                .matchHeader("Content-Type", "application/json")
                .body(newJsonBody(o ->
                        o.stringValue("accountId", "12345")
                                .numberValue("amount", 100)
                                .stringType("id", "abc123")
                ).build())
                .toPact();
    }

    @Test
    @PactVerification
    public void testProcessPayment() {
        RestTemplate restTemplate = new RestTemplate();
        final String url = pactRule.getUrl();
        log.info("Creating client for URL {}", url);
        final DefaultPaymentsClient client = new DefaultPaymentsClient(restTemplate, url);
        final PaymentDto answer = client.processPayment(new ProcessPayment("12345", 100));
        assertThat(answer.getAccountId()).isEqualTo("12345");
        assertThat(answer.getId()).isNotBlank();
        assertThat(answer.getAmount()).isEqualTo(100);
    }
}