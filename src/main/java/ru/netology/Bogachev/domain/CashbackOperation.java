package ru.netology.Bogachev.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CashbackOperation extends Operation implements ConsolePrintable{
    private int cashbackAmount;

    public CashbackOperation(int id, int cashbackAmount,  int sum, String currency, String merchant, int customerId) {
        super(id, sum, currency, merchant, customerId);
         this.cashbackAmount = cashbackAmount;
    }

    @Override
    public void printToConsole(){
        System.out.println("Values operation: " +
                "id=" + id +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                ", merchant='" + merchant + '\'' +
                ", cashbackAmount='" + cashbackAmount + '\'' +
                ';');
    }
}

