package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleApplication {
    public static void main(String[] args) {
        LottoBuyer buyer = new LottoBuyer(InputView.inputBuyMoney());

        int manualPurchaseCount = assignManualPurchaseCount(buyer);
        int autoPurchaseCount = assignAutoPurchaseCount(buyer);
        OutputView.showBuyCounts(manualPurchaseCount, autoPurchaseCount);

        OutputView.showLottos(buyer);
        LottoGameResult gameResult = assignGameResult(buyer);
        OutputView.showGameResult(gameResult);
    }

    private static int assignManualPurchaseCount(LottoBuyer buyer) {
        int manualPurchaseCount = InputView.inputManualPurchaseCount();
        int retCount = 0;
        for (; retCount < manualPurchaseCount && buyer.canBuy(); retCount++) {
            List<Integer> numbers = InputView.inputManualNumbers();
            buyer.buy(numbers);
        }
        return retCount;
    }

    private static int assignAutoPurchaseCount(LottoBuyer buyer) {
        int autoPurchaseCount = 0;
        while (buyer.canBuy()) {
            buyer.buyRandom();
            autoPurchaseCount++;
        }
        return autoPurchaseCount;
    }

    private static LottoGameResult assignGameResult(LottoBuyer buyer) {
        Lotto winningLotto = InputView.inputWinningLotto();
        LottoNumber bonusNum = InputView.inputBonusLottoNumber();
        try {
            return buyer.resultOf(winningLotto, bonusNum);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return assignGameResult(buyer);
        }
    }
}
