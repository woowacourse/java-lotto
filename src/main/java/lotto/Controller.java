package lotto;

import java.util.List;
import lotto.model.Lottos;
import lotto.model.LottosBuilder;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.LottosDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.model.prize.PrizeInformations;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {
    public static void run() {
        Money money = askMoneyAmount();
        Lottos lottos = makeLottos(money);
        givePrize(money, lottos);
    }

    private static Money askMoneyAmount() {
        return Money.from(InputView.askMoneyAmount());
    }

    private static Lottos makeLottos(Money money) {
        LottosBuilder lottosBuilder = LottosBuilder.of(money, InputView.askManualCount());
        Lottos lottos = purchaseLottos(lottosBuilder);
        ResultView.showPurchaseCount(LottosDTO.of(lottos));
        ResultView.showLottos(LottoDTO.from(lottos));
        return lottos;
    }

    private static Lottos purchaseLottos(LottosBuilder lottosBuilder) {
        InputView.askManualNumbers();
        purchaseManualLotto(lottosBuilder);
        lottosBuilder.addAutoLottos();
        return lottosBuilder.toLottos();
    }

    private static void purchaseManualLotto(LottosBuilder lottosBuilder) {
        while (lottosBuilder.isManualAvailable()) {
            lottosBuilder.addManualLotto(List.of(InputView.readNumbers()));
        }
    }

    private static void givePrize(Money money, Lottos lottos) {
        PrizeInformations prizeInformations = PrizeInformations.from(lottos.match(makeWinningLotto()));
        ResultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformations));
        ResultView.showEarningRate(prizeInformations.calculateEarningRate(money));
    }

    private static WinningLotto makeWinningLotto() {
        LottoNumbers winningNumbers = askWinningNumbers();
        return new WinningLotto(winningNumbers, askBonusNumber());
    }

    private static LottoNumbers askWinningNumbers() {
        String[] winningNumbersInput = InputView.askWinningNumbers();
        return LottoNumbers.from(List.of(winningNumbersInput));
    }

    private static LottoNumber askBonusNumber() {
        String bonusNumberInput = InputView.askBonusNumber();
        return LottoNumber.from(bonusNumberInput);
    }
}
