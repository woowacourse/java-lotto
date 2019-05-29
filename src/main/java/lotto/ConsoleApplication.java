package lotto;

import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.view.InputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        Lottos lottos = LottoMachine.buyLottos(money);
    }
}
