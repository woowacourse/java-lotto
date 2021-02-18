package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static lotto.domain.Result.RESULT_END_INDEX;
import static lotto.domain.Result.SECOND_INDEX;

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
        List<Result> fifthToFirst = getResultFromFifthToFirst();
        String[] additionalExplanation = getAdditionalExplanation();
        for (int i = 1; i < Result.values().length; i++) {
            System.out.printf("%d개 일치%s(%d원)- %d개\n", fifthToFirst.get(i).getCount(), additionalExplanation[i - 1], fifthToFirst.get(i).getPrize(), statistics.get(i - 1));
        }
        System.out.printf("총 수익률은 %.2f입니다.\n", profit);
    }

    private static List<Result> getResultFromFifthToFirst() {
        List<Result> values = Arrays.asList(Result.values());
        Collections.reverse(values);
        return values;
    }

    private static String[] getAdditionalExplanation() {
        String[] bonus = new String[RESULT_END_INDEX];
        Arrays.fill(bonus, " ");
        bonus[SECOND_INDEX] = ", 보너스 볼 일치";
        return bonus;
    }
}
