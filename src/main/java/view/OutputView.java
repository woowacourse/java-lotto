package view;

import domain.Lotto;
import domain.Lottos;
import domain.PrizeTier;
import dto.Statistics;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매하였습니다.";
    private static final String STATISTICS_HEADER_MESSAGE = "\n당첨 통계\n---------";
    private static final String STATISTICS_FORMAT = "%d개 일치%s (%d원)- %d개\n";
    private static final String BONUS_MATCHED_MESSAGE = ", 보너스 볼 일치";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.2f입니다.";

    public void printLottos(Lottos lottos) {
        List<Lotto> eachLottos = lottos.getLottos();

        System.out.printf("%d%s\n", eachLottos.size(), PURCHASE_COUNT_MESSAGE);
        eachLottos.stream()
            .map(Lotto::getNumbers)
            .forEach(System.out::println);
        System.out.println();
    }

    public void printStatistics(Statistics statistics) {
        System.out.println(STATISTICS_HEADER_MESSAGE);
        Map<PrizeTier, Integer> prizeCounts = statistics.getPrizeCounts();
        PrizeTier[] prizeTiers = PrizeTier.values();
        Arrays.sort(prizeTiers, Collections.reverseOrder());
        printPrizeTiers(prizeTiers, prizeCounts);
        System.out.printf(PROFIT_RATE_FORMAT, statistics.getProfitRate());
    }

    private void printPrizeTiers(PrizeTier[] prizeTiers, Map<PrizeTier, Integer> prizeCounts) {
        for (PrizeTier prizeTier : prizeTiers) {
            printPrizeTier(prizeCounts, prizeTier);
        }
    }

    private void printPrizeTier(Map<PrizeTier, Integer> prizeCounts, PrizeTier prizeTier) {
        int matchedCount = prizeTier.getMatchedCount();
        int prize = prizeTier.getPrize();
        int prizeTierCount = prizeCounts.get(prizeTier);

        if (prizeTier == PrizeTier.SECOND) {
            System.out.printf(STATISTICS_FORMAT, matchedCount, BONUS_MATCHED_MESSAGE, prize,
                prizeTierCount);
            return;
        }
        if (prizeTier != PrizeTier.NONE) {
            System.out.printf(STATISTICS_FORMAT, matchedCount, "", prize, prizeTierCount);
        }
    }
}
