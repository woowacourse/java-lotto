package lotto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.model.prize.MatchResult;
import lotto.model.prize.PrizeInformations;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {

    public static void run() {
        Money money = askMoneyAmount();

        Lottos lottos = initializeLottos(money);
        purchaseLottos(lottos);
        ResultView.showPurchaseCount(lottos.getSize());
        ResultView.showLottos(LottoDTO.from(lottos));

        PrizeInformations prizeInformations = PrizeInformations.from(lottos.match(makeWinningLotto()));
        ResultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformations));
        ResultView.showEarningRate(prizeInformations.calculateEarningRate(money));
    }

    private static Money askMoneyAmount() {
        return Money.from(InputView.askMoneyAmount());
    }

    private static Lottos initializeLottos(Money money) {
        int manualCount = 3;
        return new Lottos(Lotto.countAvailableTickets(money), manualCount);
    }

    private static void purchaseLottos(Lottos lottos) {
        purchaseManualLotto(lottos);
        lottos.purchaseAuto();
    }

    private static void purchaseManualLotto(Lottos lottos) {
        while (lottos.isManualAvailable()) {
            lottos.purchaseManual(List.of("1", "2", "3", "4", "5", "6"));
        }
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
