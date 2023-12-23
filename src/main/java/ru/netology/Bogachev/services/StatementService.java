package ru.netology.Bogachev.services;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.Bogachev.configuration.OperationProperties;
import ru.netology.Bogachev.domain.CashbackOperation;
import ru.netology.Bogachev.domain.Customer;
import ru.netology.Bogachev.domain.LoanOperation;
import ru.netology.Bogachev.domain.Operation;

import java.util.*;
@RequiredArgsConstructor
@Component
@Data
public class StatementService {
    private final CustomerService customerService;
    private final OperationProperties operationProperties;
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public boolean addOperation(Operation operation){
        Customer customer = customerService.getElement(operation.getCustomerId());
        if (customer == null){
            return false;
        }
        List<Operation> operationsOfCustomer = getOperations(operation.getCustomerId());
        operationsOfCustomer.add(operation);
        storage.put(operation.getCustomerId(), operationsOfCustomer);
        return true;
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

    @PostConstruct
    public void initStorage() {
        CashbackOperation cashbackOperation = new CashbackOperation(100_000,
                45,
                4500,
                "currency",
                "merchant",
                1);
        LoanOperation loanOperation = new LoanOperation(200_000,
                200_000,
                4500,
                "currency",
                "merchant",
                2);
        Operation operation = new Operation(100_001,
                3500,
                "currency",
                "merchant",
                1);

        addOperation(cashbackOperation);
        addOperation(loanOperation);
        addOperation(operation);
    }
}
