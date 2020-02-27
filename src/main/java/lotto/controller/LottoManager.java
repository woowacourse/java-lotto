package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.Money;
import lotto.domain.lottoTicket.LottoAmount;
import lotto.view.InputView;

import java.util.List;

public class LottoManager {
    public static void run() {
        Money money = new Money(InputView.inputMoney());
        LottoAmount lottoAmount = purchaseLotto(money);
        Buyer buyer = makeLottos(lottoAmount);
    }

    private static LottoAmount purchaseLotto(Money money) {
        return new LottoAmount(
                money.calculateTotalLottoAmount(),
                InputView.inputManualLottoAmount()
        );
    }

    private static Buyer makeLottos(LottoAmount lottoAmount) {
        List<String> manualLottos =
                InputView.inputManualLottoNumbers(lottoAmount.getManualLottoAmount());
        return new Buyer(manualLottos, lottoAmount);
    }
}
