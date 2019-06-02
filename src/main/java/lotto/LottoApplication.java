package lotto;

import lotto.domain.machine.Money;
import lotto.domain.machine.PurchaseInformation;
import lotto.domain.machine.VendingMachine;
import lotto.view.InputView;

public class LottoApplication {
    public void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(getMoney(Money.of(InputView.getInsertedMoney()));

    }

    public Money
}
