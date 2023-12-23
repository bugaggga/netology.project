package ru.netology.Bogachev.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.Bogachev.OperationHistoryApiApplicationTest;
import ru.netology.Bogachev.controller.dto.CustomerDTO;
import ru.netology.Bogachev.controller.dto.CustomersGetResponse;
import ru.netology.Bogachev.domain.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void getCustomerTest(){
        Customer customer1 = customerService.getElement(1);
        Customer otherCustomer = customerService.getElement(5);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertNull(otherCustomer);
    }

    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerService.getCustomers();

        Customer customer1 = customers.get(0);
        Customer customer2 = customers.get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }

    @Test
    public void setCustomerTest(){
        assertEquals("add new Customer", customerService.setElement(new Customer(5, "Client 5")));
        assertEquals("replace old Customer", customerService.setElement(new Customer(2, "Client 2 new")));

        int index = customerService.getCustomers().indexOf(new Customer(2, "Client 2 new"));
        assertEquals("Client 2 new", customerService.getCustomers().get(index).getName());

        index = customerService.getCustomers().indexOf(new Customer(5, "Client 5"));
        assertEquals("Client 5", customerService.getCustomers().get(index).getName());

        assertEquals("replace old Customer", customerService.setElement(new Customer(2, "Boot")));
        customerService.getCustomers().remove(customerService.getCustomers().indexOf(new Customer(5, "Client 5")));
    }
}
