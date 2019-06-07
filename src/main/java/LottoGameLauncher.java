import domain.*;
import domain.lottonumber.LottoNumberPool;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGameLauncher {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        List<IssuedLotto> autoIssuedLottos = LottoFactory.issueLottoWorthOf(purchaseAmount);
        OutputView.showIssuedLottos(autoIssuedLottos);

        WinningLotto winningLotto = getWinningLotto();
        Statistics statistics = LottoGame.startLottery(autoIssuedLottos, winningLotto);

        OutputView.showAnalysisOf(statistics);
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            int moneyFromPlayer = InputView.inputPurchaseAmount();
            return PurchaseAmount.of(moneyFromPlayer);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private static WinningLotto getWinningLotto() {
        try {
            List<Integer> inputNumbers = InputView.inputWinningNumbers();
            int bonusNumber = InputView.inputBonusNumber();
            return LottoFactory.issueWinningLotto(inputNumbers, LottoNumberPool.pickLottoNumber(bonusNumber));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto();
        }
    }
}
