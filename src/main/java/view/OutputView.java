package view;

import domain.Lottos;
import domain.PrizeResult;
import domain.Rank;
import java.util.Arrays;

public class OutputView {

    public static final String SHOW_BUY_COUNT = "%d개를 구매했습니다.\n";
    public static final String SHOW_STATISTICS = "당첨 통계";
    public static final String SHOW_DELIMITER = "---------";
    public static final String SHOW_PRIZE_RATE = "총 수익률은 %.2f입니다.\n";

    private static OutputView instance;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
        }
        return instance;
    }

    public void displayPrizeSummary(PrizeResult result) {
        displayPrizeSummaryIntro();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.isMiss())
                .forEach(rank -> sb.append(rank.getMsg()).append(result.findByRank(rank)).append("개\n"));
        System.out.print(sb);
        System.out.printf(SHOW_PRIZE_RATE, result.calculateProfit());
    }

    private void displayPrizeSummaryIntro() {
        displaySpacing();
        System.out.println(SHOW_STATISTICS);
        System.out.println(SHOW_DELIMITER);
    }

    public void displayLottoNumbers(Lottos lottos) {
        System.out.printf(SHOW_BUY_COUNT, lottos.size());
        System.out.printf("%s", lottos.toString());
        displaySpacing();
    }

    public void displaySpacing() {
        System.out.println();
    }
}
