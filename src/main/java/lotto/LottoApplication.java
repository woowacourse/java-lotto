package lotto;

import java.util.List;

import lotto.domain.LottoGames;
import lotto.domain.ManualCount;
import lotto.domain.TotalCount;
import lotto.domain.Number;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
import lotto.exceptions.IllegalFormatException;
import lotto.exceptions.ManualCountBoundException;
import lotto.exceptions.PurchaseAmountException;
import lotto.utils.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    private static final String NUMBER_FORMAT_EXCEPTION = "숫자 형식 오류";

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        TotalCount totalCounts = getGameCounts(purchaseAmount);
        ManualCount manualCount = getManualCount(totalCounts);
        LottoGames lottoGames = new LottoGames(manualCount.getAutoCount(totalCounts));
        OutputView.manualNumbers();
        for (int i = 0; i < manualCount.getManualCount(); i++) {
            List<Number> lottoNumbers = getManualNumbers(i);
            lottoGames.addManual(lottoNumbers);
        }
        OutputView.lottoList(lottoGames);
        WinningNumbers winningNumber = getWinLotto();
        WinningLotto winningLotto = getWinningLotto(winningNumber);
        OutputView.winList(lottoGames, winningLotto);
        OutputView.rateOfReturn(purchaseAmount);
    }

    private static List<Number> getManualNumbers(int i) {
        try {
            OutputView.indicator(i);
            return InputParser.parseLotto(InputView.getManualLotto());
        } catch (IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getManualNumbers(i);
        }
    }

    private static ManualCount getManualCount(TotalCount totalCounts) {
        try {
            int manualCount = Integer.parseInt(InputView.getManualCount());
            return ManualCount.is(manualCount, totalCounts);
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_EXCEPTION);
            return getManualCount(totalCounts);
        } catch (ManualCountBoundException e) {
            System.out.println(e.getMessage());
            return getManualCount(totalCounts);
        }
    }

    private static WinningLotto getWinningLotto(WinningNumbers winningNumbers) {
        try {
            int number = Integer.parseInt(InputView.getBonusNumber());
            return new WinningLotto(winningNumbers, Number.of(number));
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_EXCEPTION);
            return getWinningLotto(winningNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto(winningNumbers);
        }
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            return PurchaseAmount.of(InputView.getPurchaseAmount());
        } catch (PurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_EXCEPTION);
            return getPurchaseAmount();
        }
    }

    private static TotalCount getGameCounts(PurchaseAmount purchaseAmount) {
        try {
            return new TotalCount(purchaseAmount);
        } catch (PurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getGameCounts(purchaseAmount);
        }
    }

    private static WinningNumbers getWinLotto() {
        try {
            return new WinningNumbers(InputView.getWinnerLotto());
        } catch (IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getWinLotto();
        }
    }
}
