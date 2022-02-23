package lotto;

import lotto.domain.Money;
import lotto.view.InputView;

public class LottoApplication {

    public static void main(String[] args) {
        final Money money = new Money(InputView.inputMoney());
    }
}
