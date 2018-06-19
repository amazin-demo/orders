package amazin.orders.client.payment;

import amazin.kernel.payment.command.ProcessPayment;
import amazin.kernel.payment.dto.PaymentDto;

public interface PaymentsClient {
    PaymentDto processPayment(ProcessPayment command);
}
