package ru.netology.Bogachev.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.Bogachev.configuration.OperationProperties;
import ru.netology.Bogachev.domain.Operation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
@RequiredArgsConstructor
@Component
public class AsyncInputOperationService {
    private final Queue<Operation> queue = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProperties operationProperties;

    @PostConstruct
    public void init(){
        this.startAsyncOperationProcessing();
    }

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }
    public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(operationProperties.getSleepMilliSeconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation:" + operation);
                statementService.addOperation(operation);
            }
        }
    }
}
