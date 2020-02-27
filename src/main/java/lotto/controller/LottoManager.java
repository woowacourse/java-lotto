package lotto.controller;

import lotto.domain.Money;
import lotto.domain.lottoTicket.LottoAmount;
import lotto.view.InputView;

public class LottoManager {
    public static void run() {
        Money money = new Money(InputView.inputMoney());
        LottoAmount lottoAmount = purchaseLotto(money);
    }

    private static LottoAmount purchaseLotto(Money money) {
        return new LottoAmount(
                money.calculateTotalLottoAmount(),
                InputView.inputManualLottoAmount()
        );
    }
}
