package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoStatistics;
import lotto.domain.Lottos;
import lotto.domain.Rank;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.Rank.*;

public class OutputView {
    private OutputView() {
    }

    public static void showLottos(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.getNumberOfLotto());
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLottoSummary());
        }
        System.out.println();
    }

    public static void result(LottoStatistics lottoStatistics) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<Integer> statistics = lottoStatistics.getWinCountByRank();
        List<Rank> fifthToFirst = getRanksForStatistics();
        String[] additionalExplanation = getAdditionalExplanation();
        for (int i = 0; i < statistics.size(); i++) {
            System.out.printf("%d개 일치%s(%d원)- %d개\n", fifthToFirst.get(i).getCount(), additionalExplanation[i], fifthToFirst.get(i).getPrize(), statistics.get(i));
        }
        System.out.printf("총 수익률은 %.2f입니다.\n", lottoStatistics.getProfitRate());
    }

    private static String[] getAdditionalExplanation() {
        String[] bonus = new String[RANK_END_INDEX];
        Arrays.fill(bonus, " ");
        bonus[INDEX_OF_SECOND_RANK] = ", 보너스 볼 일치";
        return bonus;
    }
}
