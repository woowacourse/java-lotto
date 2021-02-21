package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();

        Lottos purchasedLottos = lottoGame.purchaseLottos(InputView.inputPurchaseMoney());
        OutputView.printPurchaseInformation(purchasedLottos);

        LottoResult result = lottoGame.calculateResult(InputView.inputWinningLotto(), purchasedLottos);
        OutputView.printResult(result);
    }
}
