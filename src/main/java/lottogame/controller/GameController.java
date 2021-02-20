package lottogame.controller;

import lottogame.domain.LottoMachine;
import lottogame.view.InputView;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.Money;
import lottogame.view.OutputView;

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
//            OutputView.printResult(matchLottos());
    }

//    private void buyLotto() {
//        int quantity = money.lottoQuantity();
//        List<Lotto> lottoGroup = LottoGenerator.makeLottos(quantity);
//        lottos = new Lottos(lottoGroup);
//        printPurchasedLottos(quantity);
//    }

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
