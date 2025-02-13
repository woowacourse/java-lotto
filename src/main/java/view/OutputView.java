package view;

import model.OwnedLotto;
import model.PrizeResult;

public class OutputView {
    public static final String SHOW_BUY_COUNT = "%d개를 구매했습니다.\n";
    public static final String SHOW_STATISTICS = "당첨 통계";
    public static final String SHOW_DELIMITER = "---------";
    public static final String SHOW_PRIZE_RATE = "총 수익률은 %.2f입니다.\n";

    public void displayPrizeSummary(PrizeResult result) {
        displayPrizeSummaryIntro();
        System.out.printf(result.toString());
        System.out.printf(SHOW_PRIZE_RATE, result.calculateProfit());
    }

    private void displayPrizeSummaryIntro() {
        displaySpacing();
        System.out.println(SHOW_STATISTICS);
        System.out.println(SHOW_DELIMITER);
    }

    public void displayLottoNumbers(OwnedLotto ownedLotto) {
        System.out.printf(SHOW_BUY_COUNT, ownedLotto.size());
        System.out.printf("%s", ownedLotto.toString());
        displaySpacing();
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        displaySpacing();
    }

    public void displaySpacing() {
        System.out.println();
    }
}