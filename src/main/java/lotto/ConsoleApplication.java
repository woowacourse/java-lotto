package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.LottosDto;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleApplication {
    private static final int START_COUNT = 0;

    public static void main(String[] args) {
        LottoService service = new LottoService(InputView.inputBuyMoney());

        int manualPurchaseCount = assignManualPurchaseCount(service);
        int autoPurchaseCount = assignAutoPurchaseCount(service);
        OutputView.showBuyCounts(manualPurchaseCount, autoPurchaseCount);

        OutputView.showLottos(createLottosDto(service.getLottos()));
        WinningLotto winningLotto = assignWinningLotto();
        LottoGameResult gameResult = service.gameResult();
        gameResult.match(winningLotto);
        OutputView.showGameResult(gameResult);
    }

    private static int assignManualPurchaseCount(final LottoService buyer) {
        int manualPurchaseCount = InputView.inputManualPurchaseCount();
        int retCount = START_COUNT;
        for (; retCount < manualPurchaseCount && buyer.canBuy(); retCount++) {
            List<Integer> numbers = InputView.inputManualNumbers();
            buyer.buy(numbers);
        }
        return retCount;
    }

    private static int assignAutoPurchaseCount(final LottoService buyer) {
        int autoPurchaseCount = START_COUNT;
        while (buyer.canBuy()) {
            buyer.buyRandom();
            autoPurchaseCount++;
        }
        return autoPurchaseCount;
    }

    private static WinningLotto assignWinningLotto() {
        Lotto lotto = InputView.inputWinningLotto();
        LottoNumber bonusNum = InputView.inputBonusLottoNumber();
        try {
            return WinningLotto.of(lotto, bonusNum);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return assignWinningLotto();
        }
    }

    private static LottosDto createLottosDto(final List<Lotto> lottos) {
        DtoConverter converter = new DtoConverter();
        return converter.convertLottosToDto(lottos);
    }
}
