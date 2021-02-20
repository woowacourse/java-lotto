package lottogame.controller;

import lottogame.domain.LottoMachine;
import lottogame.domain.lotto.WinningLotto;
import lottogame.view.InputView;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.Money;
import lottogame.view.OutputView;

import java.util.List;

public class GameController {
    private LottoMachine lottoMachine;

    public GameController() {
        lottoMachine = new LottoMachine();
    }

    public void run() {
        Money money = new Money(InputView.inputMoney());
        int quantity = lottoMachine.purchaseQuantity(money);
        Lottos lottos = new Lottos(lottoMachine.buyLotto(quantity));
        OutputView.showLottos(lottos.numbersOfLottos());
        WinningLotto winningLotto = askWinningLotto();
//        OutputView.printResult(matchLottos());
    }

    private WinningLotto askWinningLotto() {
        List<Integer> numbers = InputView.inputWinningLottoNumbers();
        int bonusBall = InputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusBall);
    }

//    private void printPurchasedLottos(int quantity) {
//        OutputView.showLottoQuantity(quantity);
//        OutputView.showLottos(lottos.values());
//    }
//
//    private LottoResults matchLottos() {
//        return lottos.findMatchLottos(inputWinningLotto(), money);
//    }
//
//    private WinningLotto inputWinningLotto() {
//        String numbers = InputView.inputWinningLottoNumbers();
//        String bonusNumber = InputView.inputBonusNumber();
//        return new WinningLotto(numbers, bonusNumber);
//    }
}
