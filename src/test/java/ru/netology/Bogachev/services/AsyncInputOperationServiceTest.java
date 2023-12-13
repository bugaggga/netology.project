package ru.netology.Bogachev.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.Bogachev.OperationHistoryApiApplicationTest;
import ru.netology.Bogachev.controller.dto.OperationDTOInput;
import ru.netology.Bogachev.domain.CashbackOperation;
import ru.netology.Bogachev.domain.LoanOperation;
import ru.netology.Bogachev.domain.Operation;

import java.util.ArrayList;
import java.util.List;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    private final static List<Operation> operationInputs = new ArrayList<>();
    @BeforeAll
    public static void inputOperation(){

        Operation operation = new Operation(54000,
                4000,
                "currency",
                "merchant",
                1);
        CashbackOperation cashbackOperation = new CashbackOperation(100000,
                100,
                10000,
                "currency",
                "merchant",
                2);
        LoanOperation loanOperation = new LoanOperation(100000,
                100000,
                5400,
                "currency",
                "merchant",
                2);

        operationInputs.add(cashbackOperation);
        operationInputs.add(loanOperation);
        operationInputs.add(operation);
    }
    @Test
    public void startProcessingTest(){
        asyncInputOperationService.startAsyncOperationProcessing();
    }

    @Test
    public void offerOperationTest(){
        for(Operation operation: operationInputs){
            asyncInputOperationService.offerOperation(operation);
        }
    }
}
