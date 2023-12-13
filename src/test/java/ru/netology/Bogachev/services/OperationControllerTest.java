package ru.netology.Bogachev.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.Bogachev.OperationHistoryApiApplicationTest;
import ru.netology.Bogachev.controller.OperationController;
import ru.netology.Bogachev.controller.dto.OperationDTOInput;
import ru.netology.Bogachev.domain.CashbackOperation;
import ru.netology.Bogachev.domain.Customer;
import ru.netology.Bogachev.domain.LoanOperation;
import ru.netology.Bogachev.domain.Operation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class OperationControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private OperationController operationController;
    @Autowired
    private StatementService statementService;
    private final static List<OperationDTOInput> operationDTOInputs = new ArrayList<>();
    @BeforeAll
    public static void inputOperation(){
        String type1 = "loan";
        String type2 = "cashback";
        Integer cashback = 100;

        OperationDTOInput operation = new OperationDTOInput(54000,
                "currency",
                "merchant",
                1,
                null,
                null);
        OperationDTOInput cashbackOperation = new OperationDTOInput(100000,
                "currency",
                "merchant",
                2,
                cashback,
                type2);
        OperationDTOInput loanOperation = new OperationDTOInput(100000,
                "currency",
                "merchant",
                2,
                null,
                type1);

        operationDTOInputs.add(cashbackOperation);
        operationDTOInputs.add(loanOperation);
        operationDTOInputs.add(operation);
    }
    @Test
    public void getOperationsTest(){
        assertEquals(statementService.getOperations(1).size(), operationController.getOperations(1).getOperations().size());
        assertNull(operationController.getOperations(5));
    }
    @Test
    public void setOperationTest(){
        for(OperationDTOInput operation: operationDTOInputs){
            operationController.setOperation(operation);
        }
    }

    @Test
    public void deleteOperationTest(){
        setOperationTest();

        operationController.deleteOperation(100_000);
        operationController.deleteOperation(200_000);
    }
}
