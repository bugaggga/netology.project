package ru.netology.Bogachev.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanOperation extends Operation implements ConsolePrintable{
    private int loanId;

    public LoanOperation(int id, int loanId, int sum, String currency, String merchant, int customerId) {
        super(id, sum, currency, merchant, customerId);
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
}
