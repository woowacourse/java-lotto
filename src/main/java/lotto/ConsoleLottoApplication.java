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
        PurchaseLottos purchaseLottos = makePurchaseLottos(purchaseAmount);
        OutputView.printPurchaseNumber(purchaseAmount);
        OutputView.printPurchaseLottoNumbers(purchaseLottos);

        WinningRule winningNumbers = InputView.inputWinningNumbers();
        Result result = new Result();
        result.calculateRank(purchaseLottos, winningNumbers);

        OutputView.printResult(purchaseAmount,result);
    }

    private static PurchaseLottos makePurchaseLottos(PurchaseAmount purchaseAmount) {
        List<Lotto> purchaseLottos = new ArrayList<>();
        purchaseLottos = InputView.inputManualLotto(purchaseAmount.getManualLottoAmount());

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < purchaseAmount.getRandomLottoAmount(); i++) {
            List<Integer> randomNumbers = randomNumberGenerator.generateNumbers();
            purchaseLottos.add(new Lotto(randomNumbers));
        }

        return new PurchaseLottos(purchaseLottos);
    }
}
