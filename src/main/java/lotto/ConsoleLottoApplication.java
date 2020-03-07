package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        try {
            lottoGame();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void lottoGame() {
        PurchaseAmount purchaseAmount = InputView.inputPurchaseAmount();
        List<Lotto> manualLottos = InputView.inputManualLotto(purchaseAmount.getManualLottoAmount());
        PurchaseLottos purchaseLottos = PurchaseLottos.makePurchaseLottos(manualLottos, purchaseAmount.getRandomLottoAmount());
        OutputView.printPurchaseNumber(purchaseAmount);
        OutputView.printPurchaseLottoNumbers(purchaseLottos);

        WinningRule winningNumbers = InputView.inputWinningNumbers();
        Result result = new Result();
        result.calculateRank(purchaseLottos, winningNumbers);

        OutputView.printResult(purchaseAmount, result);
    }
}
