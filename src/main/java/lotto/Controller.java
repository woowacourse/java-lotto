package lotto;

import java.util.List;

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
