package ru.netology.Bogachev.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Operation implements ConsolePrintable{
    protected int id;
    protected int sum;
    protected String currency;
    protected String merchant;
    protected int customerId;
    public Operation(int id, int sum, String currency, String merchant, int customerId){
        this.id = id;
        this.sum = sum;
        this.currency = currency;
        this.merchant = merchant;
        this.customerId = customerId;
    }

    public void print(){
        System.out.printf("sum: %d, currency: " + this.currency + ", merchant: " + this.merchant + "\n", this.sum);
    }

    @Override
    public void printToConsole(){
        System.out.println("Values operation: " +
                "id=" + id +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                ", merchant='" + merchant + '\'' +
                ';');
    }
}
