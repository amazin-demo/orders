package amazin.orders.client.payment;

import amazin.kernel.payment.command.ProcessPayment;
import amazin.kernel.payment.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultPaymentsClient implements PaymentsClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public DefaultPaymentsClient(RestTemplate restTemplate, @Value("${service.payments.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public PaymentDto processPayment(ProcessPayment command) {
        return restTemplate.postForObject(baseUrl + "/payments", command, PaymentDto.class);
    }
}
