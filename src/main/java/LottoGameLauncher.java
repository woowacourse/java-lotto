import domain.*;
import domain.lottonumber.LottoNumberPool;
import domain.IllegalNumberOfManualIssueException;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoGameLauncher {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        IssuedLottos manualIssuedLottos = getManualIssuedLottosUpTo(purchaseAmount);
        PurchaseAmount changeAfterManualIssue = purchaseAmount.getChangeOf(manualIssuedLottos.getPurchasedAmount());
        IssuedLottos autoIssuedLottos = LottoFactory.autoIssueLottoWorthOf(changeAfterManualIssue);
        OutputView.showIssuedLottos(manualIssuedLottos, autoIssuedLottos);

        WinningLotto winningLotto = getWinningLotto();
        IssuedLottos issuedLottos = IssuedLottos.getTotalLottosOf(manualIssuedLottos, autoIssuedLottos);
        Statistics statistics = LottoGame.startLottery(issuedLottos, winningLotto);
        OutputView.showAnalysisOf(statistics);
    }

    private static IssuedLottos getManualIssuedLottosUpTo(PurchaseAmount purchaseAmount) {
        int numberOfManualIssue = getNumberOfManualIssuingLottosUpTo(purchaseAmount);
        List<IssuedLotto> lottos = new ArrayList<>();

        InputView.printMessage("수동 구매할 로또 번호를 입력해주세요.");
        for (int i = 0; i < numberOfManualIssue; i++) {
            lottos.add(getManualIssuedLotto());
        }
        return IssuedLottos.of(lottos);
    }

    private static int getNumberOfManualIssuingLottosUpTo(PurchaseAmount purchaseAmount) {
        try {
            int numberOfMannualIssue = InputView.inputNumber();
            purchaseAmount.checkNumberOfManualIssue(numberOfMannualIssue);
            return numberOfMannualIssue;
        } catch(IllegalNumberOfManualIssueException e) {
            System.out.println(e.getMessage());
            return getNumberOfManualIssuingLottosUpTo(purchaseAmount);
        }
    }

    private static IssuedLotto getManualIssuedLotto() {
        try {
            List<Integer> numbers = InputView.getManualNumbers();
            return LottoFactory.manualIssueLottoBy(numbers);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualIssuedLotto();
        }
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            int moneyFromPlayer = InputView.inputPurchaseAmount();
            return PurchaseAmount.valueOf(moneyFromPlayer);
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
