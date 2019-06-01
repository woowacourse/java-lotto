package lotto.view;

import java.util.List;
import java.util.Scanner;

import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.exceptions.IllegalFormatException;
import lotto.domain.exceptions.LottoNumberException;
import lotto.domain.exceptions.ManualCountBoundException;
import lotto.domain.exceptions.PurchaseAmountException;
import lotto.domain.game.Count;
import lotto.domain.game.ManualCount;
import lotto.domain.game.TotalLottoGames;
import lotto.domain.lotto.Lotto;
import lotto.utils.InputParser;
import lotto.domain.lotto.Number;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static PurchaseAmount getPurchaseAmount() {
        try {
            return PurchaseAmount.is(purchaseAmount());
        } catch (PurchaseAmountException | IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private static int purchaseAmount() {
        OutputView.start();
        return InputParser.parseNumber(scanner.nextLine());
    }

    public static Count getCount(PurchaseAmount purchaseAmount) {
        try {
            return new Count(purchaseAmount);
        } catch (PurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getCount(purchaseAmount);
        }
    }

    public static ManualCount getManualCount(Count totalCount) {
        try {
            return ManualCount.is(manualCount(), totalCount);
        } catch (ManualCountBoundException | IllegalFormatException e) {
            System.out.println(e.getMessage());
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
        for (int indicator = 1; indicator <= manualCount.getCount(); indicator++) {
            List<Number> lottoNumbers = getManualNumbers(indicator);
            totalLottoGames.addManual(lottoNumbers);
        }
        return totalLottoGames;
    }

    private static List<Number> getManualNumbers(int indicator) {
        try {
            OutputView.indicator(indicator);
            return manualLotto();
        } catch (LottoNumberException | IllegalFormatException e) {
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
        } catch (IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getWinLotto();
        }
    }

    private static List<Number> winLotto() {
        OutputView.win();
        return InputParser.parseLotto(scanner.nextLine());
    }

    public static WinningLotto getWinningLotto(Lotto winningNumbers) {
        try {
            return new WinningLotto(winningNumbers, Number.of(bonusNumber()));
        } catch (IllegalFormatException | LottoNumberException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto(winningNumbers);
        }
    }

    private static int bonusNumber() {
        OutputView.bonus();
        return InputParser.parseNumber(scanner.nextLine());
    }

}
