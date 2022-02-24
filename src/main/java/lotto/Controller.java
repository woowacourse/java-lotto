package lotto;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.number.BonusNumber;
import lotto.model.number.WinningNumbers;
import lotto.model.prize.MatchResult;
import lotto.model.prize.PrizeInformations;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {

    public static void run() {
        int purchaseCount = getPurchaseCount();
        ResultView.showPurchaseCount(purchaseCount);

        Lottos lottos = Lottos.purchase(purchaseCount);
        ResultView.showLottos(LottoDTO.from(lottos));

        WinningNumbers winningNumbers = getWinningNumbers();
        BonusNumber bonusNumber = getBonusNumber(winningNumbers);

        List<MatchResult> matchResults = lottos.match(winningNumbers, bonusNumber);
        PrizeInformations prizeInformations = PrizeInformations.from(matchResults);
        ResultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformations));
    }

    private static int getPurchaseCount() {
        Money money = Money.from(InputView.askMoneyAmount());

        return Lotto.countAvailableTickets(money);
    }

    private static WinningNumbers getWinningNumbers() {
        String[] winningNumbersInput = InputView.askWinningNumbers();
        return WinningNumbers.from(winningNumbersInput);
    }

    private static BonusNumber getBonusNumber(WinningNumbers winningNumbers) {
        String bonusNumberInput = InputView.askBonusNumber();
        return BonusNumber.from(bonusNumberInput, winningNumbers);
    }
}
