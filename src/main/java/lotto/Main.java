package lotto;

import lotto.domain.*;
import lotto.view.InputConsole;
import lotto.view.OutputConsole;

public class Main {

    public static void main(String[] args) {
        Money money = createMoney();
        int numberOfLotto = money.getNumberOfLotto();
        Lottos lottos = new Lottos(numberOfLotto);
        OutputConsole.outputLotto(numberOfLotto, lottos);
        WinningLotto winningLotto = createWinningLotto(createLastWinningLotto());
        OutputConsole.outputResult(winningLotto, lottos);
    }

    private static Money createMoney() {
        try {
            return new Money(InputConsole.inputMoney());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createMoney();
        }
    }

    private static WinningLotto createWinningLotto(Lotto lastWinningLotto) {
        try {
            return new WinningLotto(lastWinningLotto, LottoNumber.get(InputConsole.inputBonusNumber()));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createWinningLotto(lastWinningLotto);
        }
    }

    private static Lotto createLastWinningLotto() {
        try {
            return new Lotto(InputConsole.inputWinningLotto());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLastWinningLotto();
        }
    }
}
