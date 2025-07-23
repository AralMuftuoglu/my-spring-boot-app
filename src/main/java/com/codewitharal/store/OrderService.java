package com.codewitharal.store;

import org.springframework.core.annotation.Order;

public class OrderService {
    public void placeOrder() {
        StripePaymentService paymentService = new StripePaymentService();
        paymentService.processPayment(12);
    }
}
