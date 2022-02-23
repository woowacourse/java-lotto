package lotto;

import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        final Money money = new Money(InputView.inputMoney());
        final int buyCounts = money.calculateLottoCount();
        OutputView.outputBuyLottoCounts(buyCounts);
    }
}
