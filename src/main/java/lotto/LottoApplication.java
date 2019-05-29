package lotto;

import java.util.List;

import lotto.domain.game.TotalLottoGames;
import lotto.domain.game.ManualCount;
import lotto.domain.game.Count;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.exceptions.IllegalFormatException;
import lotto.domain.exceptions.LottoNumberException;
import lotto.domain.exceptions.ManualCountBoundException;
import lotto.domain.exceptions.PurchaseAmountException;
import lotto.utils.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        Count totalCount = getCount(purchaseAmount);
        ManualCount manualCount = getManualCount(totalCount);
        TotalLottoGames totalLottoGames = getTotalLottoGames(totalCount, manualCount);
        OutputView.lottoList(totalLottoGames);
        Lotto winningNumber = getWinLotto();
        WinningLotto winningLotto = getWinningLotto(winningNumber);
        OutputView.winList(totalLottoGames, winningLotto);
        OutputView.rateOfReturn(purchaseAmount);
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            return PurchaseAmount.is(InputView.getPurchaseAmount());
        } catch (PurchaseAmountException | IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private static Count getCount(PurchaseAmount purchaseAmount) {
        try {
            return new Count(purchaseAmount);
        } catch (PurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getCount(purchaseAmount);
        }
    }

    private static ManualCount getManualCount(Count totalCount) {
        try {
            return ManualCount.is(InputView.getManualCount(), totalCount);
        } catch (ManualCountBoundException | IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getManualCount(totalCount);
        }
    }

    private static TotalLottoGames getTotalLottoGames(Count totalCount, ManualCount manualCount) {
        TotalLottoGames totalLottoGames = new TotalLottoGames(manualCount.getAutoCount(totalCount));
        OutputView.manualNumbers(manualCount);
        for (int indicator = 1; indicator <= manualCount.getCount(); indicator++) {
            List<Number> lottoNumbers = getManualNumbers(indicator);
            totalLottoGames.addManual(lottoNumbers);
        }
        return totalLottoGames;
    }

    private static List<Number> getManualNumbers(int indicator) {
        try {
            OutputView.indicator(indicator);
            return InputView.getManualLotto();
        } catch (LottoNumberException | IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getManualNumbers(indicator);
        }
    }

    private static Lotto getWinLotto() {
        try {
            return new Lotto(InputView.getWinnerLotto());
        } catch (IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getWinLotto();
        }
    }

    private static WinningLotto getWinningLotto(Lotto winningNumbers) {
        try {
            return new WinningLotto(winningNumbers, Number.of(InputView.getBonusNumber()));
        } catch (IllegalFormatException | LottoNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto(winningNumbers);
        }
    }
}
