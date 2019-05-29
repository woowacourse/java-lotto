package lotto;

import java.util.List;

import lotto.domain.game.TotalLottoGames;
import lotto.domain.game.ManualCount;
import lotto.domain.game.TotalCount;
import lotto.domain.lotto.Number;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
import lotto.domain.exceptions.IllegalFormatException;
import lotto.domain.exceptions.LottoNumberException;
import lotto.domain.exceptions.ManualCountBoundException;
import lotto.domain.exceptions.PurchaseAmountException;
import lotto.utils.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    private static final String NUMBER_FORMAT_EXCEPTION = "숫자 형식 오류";

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        TotalCount totalCounts = getGameCounts(purchaseAmount);
        ManualCount manualCount = getManualCount(totalCounts);
        TotalLottoGames totalLottoGames = new TotalLottoGames(manualCount.getAutoCount(totalCounts));
        OutputView.manualNumbers(manualCount);
        for (int i = 0; i < manualCount.getCount(); i++) {
            List<Number> lottoNumbers = getManualNumbers(i);
            totalLottoGames.addManual(lottoNumbers);
        }
        OutputView.lottoList(totalLottoGames);
        WinningNumbers winningNumber = getWinLotto();
        WinningLotto winningLotto = getWinningLotto(winningNumber);
        OutputView.winList(totalLottoGames, winningLotto);
        OutputView.rateOfReturn(purchaseAmount);
    }

    private static List<Number> getManualNumbers(int indicator) {
        try {
            OutputView.indicator(indicator);
            return InputParser.parseLotto(InputView.getManualLotto());
        } catch (LottoNumberException | IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getManualNumbers(indicator);
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_EXCEPTION);
            return getManualNumbers(indicator);
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
        } catch (LottoNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto(winningNumbers);
        }
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            return PurchaseAmount.is(InputView.getPurchaseAmount());
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
