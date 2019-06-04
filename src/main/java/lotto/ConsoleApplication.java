package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO Exception
// TODO depth 1
// TODO Dto
public class ConsoleApplication {
    private static final int START_COUNT = 0;

    public static void main(String[] args) {
        LottoService buyer = new LottoService(InputView.inputBuyMoney());

        int manualPurchaseCount = assignManualPurchaseCount(buyer);
        int autoPurchaseCount = assignAutoPurchaseCount(buyer);
        OutputView.showBuyCounts(manualPurchaseCount, autoPurchaseCount);

        OutputView.showLottos(createLottosDto(buyer.getLottos()));
        WinningLotto winningLotto = assignGameResult();
        LottoGameResult gameResult = buyer.gameResultOf(winningLotto);
        OutputView.showGameResult(gameResult);
    }

    private static LottosDto createLottosDto(final List<Lotto> lottos) {
        List<LottoDto> semiLottos = new ArrayList<>();
        for (final Lotto lotto : lottos) {
            LottoDto lottoDto = new LottoDto();
            lottoDto.setNumbers(lotto.getLottoNumbers().stream()
                    .map(lottoNumber -> lottoNumber.toString())
                    .collect(Collectors.toList()));
            semiLottos.add(lottoDto);
        }
        return LottosDto.of(semiLottos);
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

    private static WinningLotto assignGameResult() {
        Lotto lotto = InputView.inputWinningLotto();
        LottoNumber bonusNum = InputView.inputBonusLottoNumber();
        try {
            return WinningLotto.of(lotto, bonusNum);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return assignGameResult();
        }
    }
}
