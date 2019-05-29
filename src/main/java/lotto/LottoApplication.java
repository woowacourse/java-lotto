package lotto;

import lotto.domain.AutoCount;
import lotto.domain.ManualCount;
import lotto.domain.TotalCount;
import lotto.domain.AutoLottoGames;
import lotto.domain.LottoNumber;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
import lotto.exceptions.IllegalFormatException;
import lotto.exceptions.PurchaseAmountException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    private static final String NUMBER_FORMAT_EXCEPTION = "숫자로 입력해주세요";

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        TotalCount totalCounts = getGameCounts(purchaseAmount);
        ManualCount manualCount = ManualCount.is(InputView.getManualCount(), totalCounts);
        AutoCount autoCount = manualCount.getAutoCount(totalCounts);
        AutoLottoGames autoLottoGames = getLottoGames(totalCounts);
        OutputView.lottoList(autoLottoGames);
        WinningNumbers winningNumber = getWinLotto();
        WinningLotto winningLotto = getWinningLotto(winningNumber);
        OutputView.winList(autoLottoGames, winningLotto);
        OutputView.rateOfReturn(purchaseAmount);
    }

    private static WinningLotto getWinningLotto(WinningNumbers winningNumbers) {
        try {
            int number = Integer.parseInt(InputView.getBonusNumber());
            return new WinningLotto(winningNumbers, LottoNumber.of(number));
        } catch (NumberFormatException e) {
            System.out.println("숫자 형식 오류");
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

    private static AutoLottoGames getLottoGames(TotalCount totalCounts) {
        try {
            return new AutoLottoGames(totalCounts);
        } catch (PurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getLottoGames(totalCounts);
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
