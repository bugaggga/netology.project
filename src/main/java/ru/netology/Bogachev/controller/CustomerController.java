package ru.netology.Bogachev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.Bogachev.controller.dto.CustomerDTO;
import ru.netology.Bogachev.controller.dto.CustomersGetResponse;
import ru.netology.Bogachev.domain.Customer;
import ru.netology.Bogachev.services.CustomerService;
import ru.netology.Bogachev.services.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers/")
@RequiredArgsConstructor
public class CustomerController {
    private final StatementService statementService;
    private final CustomerService customerService;

    @GetMapping
    public CustomersGetResponse getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id){
        Customer customer = customerService.getElement(id);
        return new CustomerDTO(customer.getId(), customer.getName());
    }
    @PostMapping
    public boolean setCustomer(Customer customer){
        if (customerService.setElement(customer).equals("add new Customer")){
            return true;
        }
        return false;
    }
}
