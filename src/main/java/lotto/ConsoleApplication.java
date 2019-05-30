package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleApplication {
    public static void main(String[] args) {
        LottoService service = new LottoService(InputView.inputBuyMoney());

        int manualPurchaseCount = assignManualPurchaseCount(service);
        int autoPurchaseCount = assignAutoPurchaseCount(service);
        OutputView.showBuyCounts(manualPurchaseCount, autoPurchaseCount);

        OutputView.showLottos(service);
        LottoGameResult gameResult = assignGameResult(service);
        OutputView.showGameResult(gameResult);
    }

    private static int assignManualPurchaseCount(LottoService service) {
        int manualPurchaseCount = InputView.inputManualPurchaseCount();
        int retCount = 0;
        for (; retCount < manualPurchaseCount && service.canBuy(); retCount++) {
            List<Integer> numbers = InputView.inputManualNumbers();
            service.buy(numbers);
        }
        return retCount;
    }

    private static int assignAutoPurchaseCount(LottoService service) {
        int autoPurchaseCount = 0;
        while (service.canBuy()) {
            service.buyRandom();
            autoPurchaseCount++;
        }
        return autoPurchaseCount;
    }

    private static LottoGameResult assignGameResult(LottoService service) {
        Lotto winningLotto = InputView.inputWinningLotto();
        LottoNumber bonusNum = InputView.inputBonusLottoNumber();
        try {
            return service.resultOf(winningLotto, bonusNum);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return assignGameResult(service);
        }
    }
}
