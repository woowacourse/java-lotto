package lotto;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {

    public static void main(String[] args) {
        Money money = createMoney();
        int numberOfLotto = money.getNumberOfLotto();
        Lottos lottos = new Lottos(numberOfLotto);
        OutputView.outputLotto(numberOfLotto, lottos);
        WinningLotto winningLotto = createWinningLotto();
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

    private static WinningLotto createWinningLotto() {
        try {
            WinningLotto winningLotto = new WinningLotto(new Lotto(InputView.inputWinningLotto()));
            return winningLotto;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createWinningLotto();
        }
    }
}
