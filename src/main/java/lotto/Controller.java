package lotto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.number.LottoNumber;
import lotto.model.number.WinningNumbers;
import lotto.model.prize.MatchResult;
import lotto.model.prize.PrizeInformations;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {

    public static void run() {
        Money money = askMoneyAmount();
        PrizeInformations prizeInformations = getPrize(purchaseLottos(money), getWinningLotto());
        ResultView.showEarningRate(prizeInformations.calculateEarningRate(money));
    }

    private static Money askMoneyAmount() {
        return Money.from(InputView.askMoneyAmount());
    }

    private static PrizeInformations getPrize(Lottos lottos, WinningLotto winningLotto) {
        List<MatchResult> matchResults = lottos.match(winningLotto);

        return getPrizeInformations(matchResults);
    }

    private static PrizeInformations getPrizeInformations(List<MatchResult> matchResults) {
        PrizeInformations prizeInformations = PrizeInformations.from(matchResults);
        ResultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformations));

        return prizeInformations;
    }

    private static Lottos purchaseLottos(Money money) {
        int purchaseCount = getPurchaseCount(money);

        return purchaseLottos(purchaseCount);
    }

    private static int getPurchaseCount(Money money) {
        int purchaseCount = Lotto.countAvailableTickets(money);
        ResultView.showPurchaseCount(purchaseCount);

        return purchaseCount;
    }

    private static Lottos purchaseLottos(int purchaseCount) {
        Lottos lottos = Lottos.purchase(purchaseCount);
        ResultView.showLottos(LottoDTO.from(lottos));

        return lottos;
    }

    private static WinningLotto getWinningLotto() {
        return new WinningLotto(getWinningNumbers(), getBonusNumber());
    }

    private static WinningNumbers getWinningNumbers() {
        String[] winningNumbersInput = InputView.askWinningNumbers();

        return WinningNumbers.from(winningNumbersInput);
    }

    private static LottoNumber getBonusNumber() {
        String bonusNumberInput = InputView.askBonusNumber();

        return LottoNumber.from(bonusNumberInput);
    }

}
