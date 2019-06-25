import domain.*;
import domain.lottonumber.LottoNumber;
import utils.StringParser;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoGameLauncher {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        IssuedLottos manualIssuedLottos = getManualIssuedLottosUpTo(purchaseAmount);
        Money changeAfterManualIssue = purchaseAmount.getChangeOf(manualIssuedLottos.getPurchasedAmount());
        IssuedLottos autoIssuedLottos = LottoFactory.autoIssueLottoWorthOf(changeAfterManualIssue);
        OutputView.showIssuedLottos(manualIssuedLottos, autoIssuedLottos);

        WinningLotto winningLotto = getWinningLotto();
        IssuedLottos issuedLottos = IssuedLottos.getTotalLottosOf(manualIssuedLottos, autoIssuedLottos);
        Statistics statistics = LottoGame.startLottery(issuedLottos, winningLotto);
        OutputView.showAnalysisOf(statistics);
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            String moneyFromPlayer = InputView.inputPurchaseAmount();
            int amount = StringParser.parsePurchaseAmount(moneyFromPlayer);
            return PurchaseAmount.valueOf(amount);
        } catch (IllegalPurchasementException e) {
            System.out.println(e.getMessage());
            OutputView.show404NotFound();
            throw new RuntimeException();
        }
    }

    private static IssuedLottos getManualIssuedLottosUpTo(PurchaseAmount purchaseAmount) {
        int numberOfManualIssue = getNumberOfManualIssueLottosUpTo(purchaseAmount);
        List<IssuedLotto> lottos = new ArrayList<>();

        InputView.printInputMessageOfManualIssue();
        for (int i = 0; i < numberOfManualIssue; i++) {
            lottos.add(getManualIssuedLotto());
        }
        return IssuedLottos.of(lottos);
    }

    private static int getNumberOfManualIssueLottosUpTo(PurchaseAmount purchaseAmount) {
        try {
            String inputNumber = InputView.inputNumberOfManualIssue();
            int numberOfManualIssue = StringParser.parseInt(inputNumber);
            purchaseAmount.checkNumberOfManualIssue(numberOfManualIssue);
            return numberOfManualIssue;
        } catch (IllegalNumberOfManualIssueException e) {
            System.out.println(e.getMessage());
            OutputView.show404NotFound();
            throw new RuntimeException();
        }
    }

    private static IssuedLotto getManualIssuedLotto() {
        try {
            String inputNumbers = InputView.getNumbersForLotto();
            List<Integer> numbers = StringParser.parseNumbers(inputNumbers);
            return LottoFactory.manualIssueLottoBy(numbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            OutputView.show404NotFound();
            throw new RuntimeException();
        }
    }

    private static WinningLotto getWinningLotto() {
        try {
            List<Integer> winningNumbers = StringParser.parseNumbers(InputView.inputWinningNumbers());
            int bonusNumber = StringParser.parseInt(InputView.inputBonusNumber());
            return LottoFactory.issueWinningLotto(winningNumbers, LottoNumber.valueOf(bonusNumber));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            OutputView.show404NotFound();
            throw new RuntimeException();
        }
    }
}
