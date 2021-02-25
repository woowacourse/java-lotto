package controller;

import domain.lottoGame.LottoGame;
import domain.lottoGame.Lottos;
import domain.lottoGame.dto.LottoResult;
import domain.lottoGame.dto.PurchaseResult;
import view.InputView;
import view.OutputView;

public class LottoController {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();

        int count = lottoGame.getNumberOfAvailablePurchases(InputView.inputPurchaseMoney());

        Lottos purchasedManually = InputView.inputManualPurchaseLottos();
        PurchaseResult purchaseResults = lottoGame.purchaseLottos(purchasedManually, count);
        OutputView.printPurchaseResult(purchaseResults);

        LottoResult result = lottoGame.calculateResult(InputView.inputWinningLotto());
        OutputView.printLottoResults(result);
    }
}
