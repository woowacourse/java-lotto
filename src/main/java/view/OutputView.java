package view;

import java.util.ArrayList;
import java.util.List;
import model.OwnedLotto;
import model.result.PrizeResult;

public class OutputView {
    public static final String SHOW_BUY_COUNT = "%d개를 구매했습니다.\n";
    public static final String SHOW_STATISTICS = "당첨 통계";
    public static final String SHOW_DELIMITER = "---------";
    public static final String RESULT_RANK5 = "3개 일치 (5000원)- %d개\n";
    public static final String RESULT_RANK4 = "4개 일치 (50000원)- %d개\n";
    public static final String RESULT_RANK3 = "5개 일치 (1500000원)- %d개\n";
    public static final String RESULT_RANK2 = "5개 일치, 보너스 볼 일치(30000000원) - %d개\n";
    public static final String RESULT_RANK1 = "6개 일치 (2000000000원)- %d개\n";
    public static final String SHOW_PRIZE_RATE = "총 수익률은 %.2f입니다.\n";

    public void displayPrizeSummary(PrizeResult result) {
        displayPrizeSummaryIntro();
        System.out.printf(result.toString());

        List<Integer> valuesList = new ArrayList<>(result.getResult().values());
        System.out.printf(RESULT_RANK5, valuesList.getFirst());
        System.out.printf(RESULT_RANK4, valuesList.get(1));
        System.out.printf(RESULT_RANK3, valuesList.get(2));
        System.out.printf(RESULT_RANK2, valuesList.get(3));
        System.out.printf(RESULT_RANK1, valuesList.getLast());
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