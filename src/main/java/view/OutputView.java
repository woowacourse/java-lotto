package view;

import domain.Amount;

public class OutputView {
    public void printAmount(Amount amount) {
        System.out.printf(Output.PURCHASE_MESSAGE.getMessage(), amount.getAmount());
    }
}
