package ru.netology.Bogachev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.netology.Bogachev.configuration.OperationProperties;
import ru.netology.Bogachev.controller.dto.*;
import ru.netology.Bogachev.domain.CashbackOperation;
import ru.netology.Bogachev.domain.Customer;
import ru.netology.Bogachev.domain.LoanOperation;
import ru.netology.Bogachev.domain.Operation;
import ru.netology.Bogachev.services.AsyncInputOperationService;
import ru.netology.Bogachev.services.CustomerService;
import ru.netology.Bogachev.services.StatementService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/operations/")
@RequiredArgsConstructor
public class OperationController {
    private final StatementService statementService;
    private final AsyncInputOperationService asyncInputOperationService;
    private final OperationProperties operationProperties;
    private final CustomerService customerService;

    @GetMapping("{id}")
    public OperationsGetResponse getOperations(@PathVariable("id") int customerId){
        if (statementService.getOperations(customerId) == null) return null;
        List<Operation> operations = statementService.getOperations(customerId);
        List<OperationDTO> operationDTOS = operations.stream()
                .map(operation -> new OperationDTO(operation.getId(), operation.getSum(), operation.getCurrency()))
                .collect(Collectors.toList());
        return new OperationsGetResponse(operationDTOS);
    }
    @PostMapping
    public boolean  setOperation(OperationDTOInput operationInput){
        int customerId = operationInput.getCustomerId();

        if (statementService.getOperations(customerId) == null) {
            return false;
        }

        int operationId = customerId * operationProperties.getMaxCountOperations() + statementService.getOperations(customerId).size();

        if (operationInput.getType() == null){
            asyncInputOperationService.offerOperation(new Operation(operationId,
                    operationInput.getSum(),
                    operationInput.getCurrency(),
                    operationInput.getMerchant(),
                    customerId));
            return true;
        }
        if (operationInput.getType().equals("loan")) {
            asyncInputOperationService.offerOperation(new LoanOperation(operationId,
                    operationId,
                    operationInput.getSum(),
                    operationInput.getCurrency(),
                    operationInput.getMerchant(),
                    customerId));
            return true;
        }
        asyncInputOperationService.offerOperation(new CashbackOperation(operationId,
                operationInput.getCashbackAmount(),
                operationInput.getSum(),
                operationInput.getCurrency(),
                operationInput.getMerchant(),
                customerId));
        return true;
    }

    @DeleteMapping
    public boolean deleteOperation(int operationId){
        return statementService.deleteOperation(operationId);
    }
}
