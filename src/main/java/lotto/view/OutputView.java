package lotto.view;

import lotto.domain.MyLotto;
import lotto.domain.Rank;

import java.util.List;

public class OutputView {
    private static final String MESSAGE_BUY_LOTTO = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String RESULT = "\n당첨 통계";
    private static final String BASIC_BAR = "--------";
    private static final String RETURN_RATE = "총 수입률은 %.0f%%입니다.";
    private static final String RESULT_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개";

    public static void printMyLotto(MyLotto myLotto, int round) {
        System.out.println(String.format(MESSAGE_BUY_LOTTO, myLotto.getSize() - round, round));

        for (int i = 0; i < myLotto.getSize(); i++) {
            System.out.println(myLotto.getIndexByLotto(i).toString());
        }
    }

    public static void printResultStatus(List<Rank> ranks, double returnRate) {
        System.out.println(RESULT);
        System.out.println(BASIC_BAR);

        for (Rank rank : Rank.values()) {
            getResult(ranks, rank);
        }

        System.out.printf(RETURN_RATE, returnRate);
    }

    private static void getResult(List<Rank> ranks, Rank rank) {
        if (Rank.MISS.equals(rank)) {
            return;
        }

        if (Rank.SECOND.equals(rank)) {
            System.out.println(String.format(RESULT_BONUS_FORMAT, rank.getCount(), rank.getPrize(), rank.getMatchingCount(ranks)));
            return;
        }

        System.out.println(String.format(RESULT_FORMAT, rank.getCount(), rank.getPrize(), rank.getMatchingCount(ranks)));
    }
}
