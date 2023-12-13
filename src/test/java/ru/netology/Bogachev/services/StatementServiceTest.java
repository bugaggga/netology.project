package ru.netology.Bogachev.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.Bogachev.OperationHistoryApiApplicationTest;
import ru.netology.Bogachev.domain.CashbackOperation;
import ru.netology.Bogachev.domain.Customer;
import ru.netology.Bogachev.domain.LoanOperation;
import ru.netology.Bogachev.domain.Operation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private StatementService statementService;
    private final static List<Operation> operationList = new ArrayList<>();
    @BeforeAll
    public static void inputOperation(){
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

        Operation operation1 = cashbackOperation;
        Operation operation2 = loanOperation;

        operationList.add(cashbackOperation);
        operationList.add(loanOperation);
        operationList.add(operation1);
        operationList.add(operation2);
    }
    @Test
    public void addOperationTest(){
        for(Operation operation: operationList){
            statementService.addOperation(operation);
        }
    }

    @Test
    public void getOperationsTest(){
        addOperationTest();
        int sizeCustomer1 = statementService.getOperations(1).size();
        int sizeCustomer2 = statementService.getOperations(2).size();

        assertEquals(2, sizeCustomer1);
        assertEquals(2, sizeCustomer2);
        assertNull(statementService.getOperations(5));
    }

    @Test
    public void deleteOperationsTest(){
        addOperationTest();

        boolean bool = statementService.deleteOperation(100000);
        assertEquals(true, bool);

        bool = statementService.deleteOperation(1000);
        assertEquals(false, bool);

    }
}
