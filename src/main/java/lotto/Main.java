package lotto;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {

    public static void main(String[] args) {
        Money money = createMoney();
        int numberOfLotto = money.getNumberOfLotto();
        Lottos lottos = new Lottos(numberOfLotto);
        OutputView.outputLotto(numberOfLotto, lottos);
    }

    private static Money createMoney() {
        try {
            Money money = new Money(InputView.inputMoney());
            return money;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createMoney();
        }
    }
}
