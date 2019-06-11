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
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualCount(totalCount);
        }
    }

    private static int manualCount() {
        OutputView.manualCount();
        return InputParser.parseNumber(scanner.nextLine());
    }


    public static TotalLottoGames getTotalLottoGames(Count totalCount, ManualCount manualCount) {
        TotalLottoGames totalLottoGames = new TotalLottoGames(manualCount.autoCount(totalCount));
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
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualNumbers(indicator);
        }
    }

    private static List<Number> manualLotto() {
        return InputParser.parseLotto(scanner.nextLine());
    }

    public static Lotto getWinLotto() {
        try {
            return new Lotto(winLotto());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinLotto();
        }
    }

    private static List<Number> winLotto() {
        try {
            OutputView.win();
            return InputParser.parseLotto(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return winLotto();
        }
    }

    public static WinningLotto getWinningLotto(Lotto winningNumbers) {
        try {
            return new WinningLotto(winningNumbers, Number.of(bonusNumber()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto(winningNumbers);
        }
    }

    private static int bonusNumber() {
        OutputView.bonus();
        return InputParser.parseNumber(scanner.nextLine());
    }

}
