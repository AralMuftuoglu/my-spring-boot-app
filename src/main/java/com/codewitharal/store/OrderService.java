package com.codewitharal.store;

import org.springframework.core.annotation.Order;

public class OrderService {

    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(12);
    }
}
