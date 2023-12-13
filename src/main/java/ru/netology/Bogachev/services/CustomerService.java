package ru.netology.Bogachev.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.Bogachev.domain.Customer;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Component
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();
    public void setElement(Customer element) {
        Customer customer = getElement(element.getId());
        if (customer != null){
            int index = customers.indexOf(customer);
            customers.get(index).setName(element.getName());
            return;
        }
        customers.add(element);
    }

    public Customer getElement(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst().orElse(null);
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    @PostConstruct
    public void initStorage() {
        customers.add(new Customer(1,"Spring"));
        customers.add(new Customer(2, "Boot"));
    }
}
