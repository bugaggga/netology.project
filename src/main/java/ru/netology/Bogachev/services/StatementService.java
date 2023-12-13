package ru.netology.Bogachev.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.Bogachev.configuration.OperationProperties;
import ru.netology.Bogachev.domain.Customer;
import ru.netology.Bogachev.domain.Operation;

import java.util.*;
@RequiredArgsConstructor
@Component
@Data
public class StatementService {
    private final CustomerService customerService;
    private final OperationProperties operationProperties;
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public void addOperation(Operation operation){
        Customer customer = customerService.getElement(operation.getCustomerId());
        if (customer == null){
            return;
        }
        List<Operation> operationsOfCustomer = getOperations(operation.getCustomerId());
        operationsOfCustomer.add(operation);
        storage.put(operation.getCustomerId(), operationsOfCustomer);
    }

    public List<Operation> getOperations(int clientId){
        if (customerService.getElement(clientId) == null){
            return null;
        }
        List<Operation> operations = storage.get(clientId);
        if (operations == null){
            storage.put(clientId, new ArrayList<>());
            return storage.get(clientId);
        }
        return operations;
    }

    public boolean deleteOperation(int operationId) {
        try {
            int customerId = operationId / operationProperties.getMaxCountOperations();
            List<Operation> operationsOfCustomer = getOperations(customerId);
            operationsOfCustomer.remove(operationId % operationProperties.getMaxCountOperations());
            operationsOfCustomer.stream()
                            .peek(operation -> {
                                if (operation.getId() > operationId){
                                    storage.get(customerId).get(getIndexById(operation.getId())).setId(operation.getId()-1);
                                }
                            });
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    private int getIndexById(int operationId){
        int customerId = operationId / operationProperties.getMaxCountOperations();
        List<Operation> list = getOperations(customerId);
        return list.stream()
                .filter(operation -> operation.getId() == operationId)
                .map(operation -> operation.getId() % operationProperties.getMaxCountOperations())
                .findFirst().orElse(-1);
    }
}
