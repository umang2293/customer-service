package com.ecommerce.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private static final String ORDER_SERVICE_HOST = "http://localhost:8082";
    private static final String ORDER_GET_URL = "/api/v1/orders/getOrder/";

    private final WebClient webClient;
    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;

    public CustomerService(WebClient.Builder webClientBuilder,
                           RestTemplate restTemplate,
                           CustomerRepository customerRepository) {
        this.webClient = webClientBuilder.baseUrl(ORDER_SERVICE_HOST).build();
        this.restTemplate = restTemplate;
        this.customerRepository = customerRepository;
    }

    public String getOrderDetails(Long orderId) {
        return restTemplate.getForObject(ORDER_SERVICE_HOST + ORDER_GET_URL + orderId, String.class);
    }

    public Mono<String> getOrderDetailsAsync(Long orderId) {
        return webClient.get()
                .uri(ORDER_GET_URL + orderId)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        return customerRepository.save(customer);
    }
}
