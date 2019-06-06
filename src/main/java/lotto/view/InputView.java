package lotto.view;

import java.util.List;
import java.util.Scanner;

import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.exceptions.BonusNumberException;
import lotto.exceptions.CountRangeException;
import lotto.exceptions.IllegalFormatException;
import lotto.exceptions.NumberCountException;
import lotto.exceptions.LottoNumberException;
import lotto.exceptions.PurchaseAmountException;
import lotto.exceptions.PurchaseUnitException;
import lotto.domain.game.Count;
import lotto.domain.game.ManualCount;
import lotto.domain.game.TotalLottoGames;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.utils.InputParser;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static PurchaseAmount getPurchaseAmount() {
        try {
            return PurchaseAmount.of(purchaseAmount());
        } catch (PurchaseAmountException e) {
            ErrorView.purchaseAmount();
            return getPurchaseAmount();
        } catch (PurchaseUnitException e) {
            ErrorView.purchaseUnit();
            return getPurchaseAmount();
        } catch (NumberFormatException e) {
            ErrorView.numberFormat();
            return getPurchaseAmount();
        }
    }

    private static int purchaseAmount() {
        OutputView.start();
        return InputParser.parseNumber(scanner.nextLine());
    }

    public static Count countFrom(PurchaseAmount purchaseAmount) {
        return new Count(purchaseAmount);
    }

    public static ManualCount getManualCount(Count totalCount) {
        try {
            return ManualCount.is(manualCount(), totalCount);
        } catch (CountRangeException e) {
            ErrorView.countRange();
            return getManualCount(totalCount);
        } catch (NumberFormatException e) {
            ErrorView.numberFormat();
            return getManualCount(totalCount);
        }
    }

    private static int manualCount() {
        OutputView.manualCount();
        return InputParser.parseNumber(scanner.nextLine());
    }


    public static TotalLottoGames getTotalLottoGames(Count totalCount, ManualCount manualCount) {
        TotalLottoGames totalLottoGames = new TotalLottoGames(manualCount.getAutoCount(totalCount));
        OutputView.manualNumbers(manualCount);
        for (int indicator = 1; manualCount.isUnder(indicator); indicator++) {
            List<Number> lottoNumbers = getManualNumbers(indicator);
            totalLottoGames.addManual(lottoNumbers);
        }
        return totalLottoGames;
    }

    private static List<Number> getManualNumbers(int indicator) {
        try {
            OutputView.indicator(indicator);
            return manualLotto();
        } catch (IllegalFormatException | NumberFormatException e) {
            ErrorView.numberFormat();
            return getManualNumbers(indicator);
        } catch (NumberCountException e) {
            ErrorView.numberCount();
            return getManualNumbers(indicator);
        } catch (LottoNumberException e) {
            ErrorView.lottoNumber();
            return getManualNumbers(indicator);
        }
    }

    private static List<Number> manualLotto() {
        return InputParser.parseLotto(scanner.nextLine());
    }

    public static Lotto getWinLotto() {
        try {
            return new Lotto(winLotto());
        } catch (NumberFormatException e) {
            ErrorView.numberFormat();
            return getWinLotto();
        } catch (NumberCountException e) {
            ErrorView.numberCount();
            return getWinLotto();
        }
    }

    private static List<Number> winLotto() {
        try {
            OutputView.win();
            return InputParser.parseLotto(scanner.nextLine());
        } catch (LottoNumberException e) {
            ErrorView.lottoNumber();
            return winLotto();
        }
    }

    public static WinningLotto getWinningLotto(Lotto winningNumbers) {
        try {
            return new WinningLotto(winningNumbers, Number.of(bonusNumber()));
        } catch (IllegalFormatException | NumberFormatException e) {
            ErrorView.numberFormat();
            return getWinningLotto(winningNumbers);
        } catch (LottoNumberException e) {
            ErrorView.lottoNumber();
            return getWinningLotto(winningNumbers);
        } catch (BonusNumberException e) {
            ErrorView.bonusNumber();
            return getWinningLotto(winningNumbers);
        }
    }

    private static int bonusNumber() {
        OutputView.bonus();
        return InputParser.parseNumber(scanner.nextLine());
    }

}
