package lotto;

import lotto.domain.LottoGameResult;
import lotto.domain.LottoNumber;
import lotto.domain.LottoService;
import lotto.domain.exception.LottoNumberCreateException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleApplication {

    public static void main(String[] args) {
        LottoService service = assignService();

        int manualPurchaseCount = assignManualPurchaseCount(service);
        int autoPurchaseCount = assignAutoPurchaseCount(service);

        OutputView.showBuyCounts(manualPurchaseCount, autoPurchaseCount);   // 수동으로, 자동으로 출력
        LottoGameResult gameResult = assignGameResult(service);
        OutputView.showGameResult(gameResult);
    }

    private static int assignAutoPurchaseCount(LottoService service) {
        int autoPurchaseCount = 0;
        while (service.canBuy()) {
            service.buyRandom();
            autoPurchaseCount++;
        }
        return autoPurchaseCount;
    }

    private static LottoService assignService() {
        int money = InputView.inputBuyMoney();
        try {
            return new LottoService(money);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return assignService();
        }
    }

    private static int assignManualPurchaseCount(LottoService service) {
        int manualPurchaseCount = InputView.inputManualPurchaseCount();
        int i = 0;
        for (; i < manualPurchaseCount && service.canBuy(); i++) {
            List<Integer> numbers = InputView.inputManualNumbers();
            service.buy(numbers);
        }
        return i;
    }

    private static LottoGameResult assignGameResult(LottoService service) {
        List<Integer> winningNumbers = assignWinningNumbers();
        LottoNumber bonusNum = assignBonusNum();
        try {
            return service.result(winningNumbers, bonusNum);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return assignGameResult(service);
        }
    }

    private static LottoNumber assignBonusNum() {
        int bonusNum = InputView.inputBonusNumber();
        try {
            return LottoNumber.of(bonusNum);
        } catch (LottoNumberCreateException e) {
            System.out.println(e.getMessage());
            return assignBonusNum();
        }
    }

    private static List<Integer> assignWinningNumbers() {
        return InputView.inputWinningNumbers();
    }
}
