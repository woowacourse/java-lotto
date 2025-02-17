package lotto.view;

import java.util.List;

public class OutputView {

    private static final String PURCHASE_MESSAGE = "%d개를 구매했습니다.";
    private static final String RESULT_HEADER = "당첨 통계";
    private static final String RESULT_MULTI_DASH = "---------";
    private static final String PRIZE_MESSAGE = "%d개 일치 (%d원)- %d개";
    private static final String PRIZE_MESSAGE_MATCH_BONUS = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String PROFIT_MESSAGE = "총 수익률은 %.2f입니다.";

    public void printCount(final int count) {
        System.out.println(String.format(PURCHASE_MESSAGE, count));
    }

    public void printLotto(List<Integer> lotto) {
        System.out.println(lotto);
    }

    public void printResultHeader() {
        System.out.println(System.lineSeparator() + RESULT_HEADER);
        System.out.println(RESULT_MULTI_DASH);
    }

    public void printProfitRate(double profitRate) {
        System.out.println(String.format(PROFIT_MESSAGE, profitRate));
    }

    public void printResult(int matchCounts, int money, boolean matchBonus, int count) {
        if (matchBonus) {
            System.out.println(String.format(PRIZE_MESSAGE_MATCH_BONUS, matchCounts, money, count));
            return;
        }
        System.out.println(String.format(PRIZE_MESSAGE, matchCounts, money, count));
    }
}
