package ru.netology.Bogachev.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.Bogachev.OperationHistoryApiApplicationTest;
import ru.netology.Bogachev.controller.dto.CustomerDTO;
import ru.netology.Bogachev.controller.dto.CustomersGetResponse;
import ru.netology.Bogachev.domain.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        int sizeStorageBeforeSet = customerService.getCustomers().size();
        customerService.setElement(new Customer(5, "Client 5"));
        sizeStorageBeforeSet+=1;
        assertEquals(customerService.getCustomers().size(), sizeStorageBeforeSet);

        customerService.setElement(new Customer(2, "Client 2 new"));
        assertEquals(customerService.getCustomers().size(), sizeStorageBeforeSet);

        int index = customerService.getCustomers().indexOf(new Customer(2, "Client 2 new"));
        assertEquals(customerService.getCustomers().get(index).getName(), "Client 2 new");

        index = customerService.getCustomers().indexOf(new Customer(5, "Client 5"));
        assertEquals(customerService.getCustomers().get(index).getName(), "Client 5");
    }
}
