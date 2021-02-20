package lottogame.controller;

import lottogame.domain.LottoMachine;
import lottogame.view.InputView;
import lottogame.view.OutputView;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoGenerator;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.Money;
import lottogame.domain.lotto.WinningLotto;
import lottogame.domain.dto.LottoResults;

import java.util.List;

public class GameController {
    private LottoMachine lottoMachine;

    public GameController() {
        lottoMachine = new LottoMachine();
        LottoGenerator.generate();
    }

    public void run() {
        Money money = new Money(InputView.inputMoney());
        int quantity = lottoMachine.purchaseQuantity(money);
//            buyLotto();
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
