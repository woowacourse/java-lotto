package view;

import domain.Lotto.Lotto;
import domain.Lotto.Lottos;
import domain.Rank;

import java.util.Map;

public class OutputView {

    private static final String PURCHASE_QUANTITY_FORMAT = "%s개를 구매했습니다.%n";
    private static final int SECOND_RANK = 2;
    private static final String RESULT_MESSAGE = "당첨 통계";
    private static final String DELIMITER = "---------";
    private static final String RESULT_FORMAT = "%d개 일치%s (%d원)- %d개%n";
    private static final String BONUS_FORMAT = ", 보너스 볼 일치";
    private static final String EMPTY_FORMAT = "";
    private static final String INCOME_RATE_FORMAT = "총 수익률은 %.2f입니다.%n";
    private static final String LOTTO_COUNT_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";

    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void printPurchasedLotto(int manualLottoCount, int autoLottoCount, Lottos lottos) {
        System.out.print(System.lineSeparator());
        System.out.printf(LOTTO_COUNT_FORMAT, manualLottoCount, autoLottoCount);
        for (Lotto lotto : lottos.getLottos()) {
            printLottoNumbers(lotto);
        }
        System.out.print(System.lineSeparator());
    }

    public void printLottoNumbers(Lotto lotto) {
        System.out.println(lotto.getLotto());
    }

    public void printResult(Map<Rank, Integer> resultStatistics) {
        System.out.print(System.lineSeparator());
        System.out.println(RESULT_MESSAGE);
        System.out.println(DELIMITER);
        for (Rank rank : resultStatistics.keySet()) {
            printByRank(rank.getRankNumber(), rank.getCriteria(), rank.getReward(), resultStatistics.get(rank));
        }
    }

    private void printByRank(int rankNumber, int criteria, int reward, int hitCount) {
        if (rankNumber == SECOND_RANK) {
            System.out.printf((RESULT_FORMAT), criteria, BONUS_FORMAT, reward, hitCount);
            return;
        }
        System.out.printf((RESULT_FORMAT), criteria, EMPTY_FORMAT, reward, hitCount);
    }

    public void printIncomeRate(double incomeRate) {
        System.out.printf((INCOME_RATE_FORMAT), incomeRate);
    }
}
