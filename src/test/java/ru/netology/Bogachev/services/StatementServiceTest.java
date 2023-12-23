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
        CashbackOperation cashbackOperation = new CashbackOperation(100_002,
                45,
                4500,
                "currency",
                "merchant",
                1);
        LoanOperation loanOperation = new LoanOperation(200_001,
                200_001,
                4500,
                "currency",
                "merchant",
                2);

        operationList.add(cashbackOperation);
        operationList.add(loanOperation);
    }
    @Test
    public void addOperationTest(){
        for(Operation operation: operationList){
            assertTrue(statementService.addOperation(operation));
        }
    }

    @Test
    public void getOperationsTest(){
        addOperationTest();
        List<Operation> listCustomer1 = statementService.getOperations(1);
        List<Operation> listCustomer2 = statementService.getOperations(2);
        List<Operation> listCustomerOther = statementService.getOperations(5);
        assertNotNull(listCustomer1);
        assertNotNull(listCustomer2);
        assertNull(listCustomerOther);
    }

    @Test
    public void deleteOperationsTest(){
        addOperationTest();

        boolean bool = statementService.deleteOperation(100000);
        assertTrue(bool);

        bool = statementService.deleteOperation(1000);
        assertFalse(bool);

    }
}
