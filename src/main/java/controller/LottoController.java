package controller;

import domain.lottoGame.LottoGame;
import domain.lottoGame.Lottos;
import domain.dto.GameResult;
import domain.dto.PurchaseResult;
import view.InputView;
import view.OutputView;

public class LottoController {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame(InputView.inputPurchaseMoney());

        purchaseLotto(lottoGame);

        calculateGameResult(lottoGame);
    }

    private static void purchaseLotto(LottoGame lottoGame) {
        PurchaseResult purchaseResult = lottoGame.purchaseLottos(InputView.inputManualPurchaseLottos());
        OutputView.printPurchaseResult(purchaseResult);
    }

    private static void calculateGameResult(LottoGame lottoGame) {
        GameResult gameResult = lottoGame.calculateResult(InputView.inputWinningLotto());
        OutputView.printLottoResults(gameResult);
    }
}
