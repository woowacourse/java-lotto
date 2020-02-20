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
        PurchaseAmount purchaseAmount = InputView.inputPurchaseMoney();
        OutputView.printPurchaseNumber(purchaseAmount);

        PurchaseLottos purchaseLottos = makePurchaseLottos(purchaseAmount);
        OutputView.printPurchaseLottoNumbers(purchaseLottos);

        WinningRule winningNumbers = InputView.inputWinningNumbers();
        winningNumbers.calculateWinningResult(purchaseLottos);

        OutputView.printResult(purchaseAmount);
    }

    private static PurchaseLottos makePurchaseLottos(PurchaseAmount purchaseAmount) {
        List<Lotto> purchaseLottoNumbers = new ArrayList<>();
        for (int i = 0; i < purchaseAmount.getPurchaseNumber(); i++) {
            List<Integer> randomNumbers = RandomNumberGenerator.generateNumbers();
            purchaseLottoNumbers.add(new Lotto(randomNumbers));
        }
        return new PurchaseLottos(purchaseLottoNumbers);
    }
}
