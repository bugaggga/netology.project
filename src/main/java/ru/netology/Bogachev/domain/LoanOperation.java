package ru.netology.Bogachev.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public class LoanOperation extends Operation implements ConsolePrintable{
    private int loanId;

    public LoanOperation(int id, int loanId, int sum, String currency, String merchant, int customerId) {
        super(id, sum, currency, merchant, customerId);
        this.loanId = loanId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    @Override
    public void printToConsole(){
        System.out.println("Values operation: " +
                "id=" + id +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                ", merchant='" + merchant + '\'' +
                ", loanId='" + loanId + '\'' +
                ';');
    }

    @Override
    public String toString() {
        return "LoanOperation{" +
                "id=" + id +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                ", merchant='" + merchant + '\'' +
                ", loanId=" + loanId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return this.sum * 1337+this.merchant.hashCode()*1337;
    }
}
