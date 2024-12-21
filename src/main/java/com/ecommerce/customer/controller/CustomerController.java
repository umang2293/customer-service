package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.CustomerDto;
import com.ecommerce.customer.service.CustomerService;
import com.ecommerce.customer.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @GetMapping("/order/{orderId}")
    public String getOrderDetails(@PathVariable Long orderId) {
        return customerService.getOrderDetails(orderId);
    }

    @GetMapping("/order/async/{orderId}")
    public Mono<String> getOrderDetailsAsync(@PathVariable Long orderId) {
        return customerService.getOrderDetailsAsync(orderId);
    }
}
