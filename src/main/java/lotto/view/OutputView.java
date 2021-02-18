package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OutputView {
    public static void showLottos(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.getNumberOfLotto());
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLottoSummary());
        }
        System.out.println();
    }

    public static void result(List<Result> results, float profit) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<Integer> statistics = Result.getStatistics(results);
        List<Result> values = Arrays.asList(Result.values());
        Collections.reverse(values);
        String[] additionalExplanation = getAdditionalExplanation();
        for (int i = 1; i < Result.values().length; i++) {
            System.out.printf("%d개 일치%s(%d원)- %d개\n", values.get(i).getCount(), additionalExplanation[i - 1], values.get(i).getPrize(), statistics.get(i - 1));
        }
        System.out.printf("총 수익률은 %.2f입니다.\n", profit);
    }

    private static String[] getAdditionalExplanation() {
        String[] bonus = new String[5];
        Arrays.fill(bonus, " ");
        bonus[3] = ", 보너스 볼 일치";
        return bonus;
    }
}
