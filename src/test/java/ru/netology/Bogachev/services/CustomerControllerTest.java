package ru.netology.Bogachev.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.Bogachev.OperationHistoryApiApplicationTest;
import ru.netology.Bogachev.controller.CustomerController;
import ru.netology.Bogachev.controller.dto.CustomerDTO;
import ru.netology.Bogachev.controller.dto.CustomersGetResponse;
import ru.netology.Bogachev.domain.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerController customerController;
    @Autowired
    private CustomerService customerService;

    @Test
    public void getClientsTest(){
        CustomersGetResponse customers = customerController.getCustomers();
        CustomerDTO customer1 = customers.getCustomers().get(0);
        CustomerDTO customer2 = customers.getCustomers().get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }

    @Test
    public void aggGetClientTest(){
        for (Customer customer: customerService.getCustomers()){
            getClientTest(customer);
        }
    }
    private void getClientTest(Customer customer){
        CustomerDTO customerDTO = customerController.getCustomer(customer.getId());

        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getName(), customerDTO.getName());
    }

    @Test
    public void setClientTest(){
        int sizeStorageBeforeSet = customerController.getCustomers().getCustomers().size();
        customerController.setCustomer(new Customer(5, "Client 5"));
        sizeStorageBeforeSet+=1;
        assertEquals(customerController.getCustomers().getCustomers().size(), sizeStorageBeforeSet);

        customerController.setCustomer(new Customer(2, "Client 2 new"));
        assertEquals(customerController.getCustomers().getCustomers().size(), sizeStorageBeforeSet);

        int index = customerService.getCustomers().indexOf(new Customer(2, "Client 2 new"));
        assertEquals(customerService.getCustomers().get(index).getName(), "Client 2 new");

        index = customerService.getCustomers().indexOf(new Customer(5, "Client 5"));
        assertEquals(customerService.getCustomers().get(index).getName(), "Client 5");
    }
}
