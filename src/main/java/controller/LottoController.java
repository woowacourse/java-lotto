package controller;

import domain.Money;
import domain.lottoGame.LottoGame;
import domain.lottoGame.LottoResult;
import domain.lottoGame.Lottos;
import view.InputView;
import view.OutputView;

public class LottoController {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();

        int count = lottoGame.getNumberOfAvailablePurchases(InputView.inputPurchaseMoney());
        Lottos purchasedLottos = lottoGame.purchaseLottos(count);
        OutputView.printPurchaseInformation(purchasedLottos);

        LottoResult result = lottoGame.calculateResult(InputView.inputWinningLotto(), purchasedLottos);
        OutputView.printResult(result);
    }
}
