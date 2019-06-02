package lotto;

import lotto.domain.machine.MoneyProcessor;
import lotto.domain.machine.VendingMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(createMoneyProcessor());

    }

    public static MoneyProcessor createMoneyProcessor() {
        try {
            MoneyProcessor moneyProcessor = MoneyProcessor.of(InputView.getInsertedMoney());
            OutputView.printInsertedMoneyInformation(moneyProcessor.getWholeTicketQuantity(),moneyProcessor.getRest());
            return moneyProcessor;
        } catch (Exception e) {
            System.out.println();
            return createMoneyProcessor();
        }
    }

}
