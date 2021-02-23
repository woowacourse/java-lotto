package lottogame;

import lottogame.domain.Money;
import lottogame.domain.lotto.LottoGame;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.lotto.WinningLotto;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        LottoGame lottoGame = new LottoGame(money);
        printPurchasedLottos(lottoGame.lottos());
        WinningLotto winningLotto = inputWinningLotto();
        OutputView.printResult(lottoGame.Results(winningLotto));
    }

    private static void printPurchasedLottos(Lottos lottos) {
        OutputView.showLottoQuantity(lottos.values().size());
        OutputView.showLottos(lottos.values());
    }

    private static WinningLotto inputWinningLotto() {
        String numbers = InputView.inputWinningLottoNumbers();
        String bonusNumber = InputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusNumber);
    }
}
