package lotto.view;

import com.google.common.primitives.Ints;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OutputView {
    private static final int LOTTO_RESULT_SIZE = 5;
    private static final int LOTTO_SECOND_INDEX = 3;

    private OutputView() {
    }

    public static void showBuyLotto(List<Lotto> lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.size());
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append("[");
            sb.append(Ints.join(", ", lotto.getLottoNumbers().stream().mapToInt(i -> i).toArray()));
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }

    public static void result(List<Result> results, float profit) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<Integer> statistics = Result.getStatistics(results);
        List<Result> values = getValues();
        String[] bonus = getBonusStatus();
        for (int i = 1; i < Result.values().length; i++) {
            Result result = values.get(i);
            printResult(result.getCount(), bonus[i - 1], result.getPrize(), statistics.get(i -1));
        }
        System.out.printf("총 수익률은 %.2f입니다.\n", profit);
    }

    private static void printResult(int count, String bonus, int prize, int statistic) {
        System.out.printf("%d개 일치%s(%d원)- %d개\n", count, bonus, prize, statistic);
    }

    private static List<Result> getValues() {
        List<Result> values = Arrays.asList(Result.values());
        Collections.reverse(values);
        return values;
    }

    private static String[] getBonusStatus() {
        String[] bonus = new String[LOTTO_RESULT_SIZE];
        Arrays.fill(bonus, " ");
        bonus[LOTTO_SECOND_INDEX] = ", 보너스 볼 일치";

        return bonus;
    }
}
