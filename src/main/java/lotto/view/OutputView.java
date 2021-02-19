package lotto.view;

import lotto.domain.LottoStatisticResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.RankComparator;

import java.util.Arrays;
import java.util.Collections;

public class OutputView {
    
    private static final String VIEW_OF_LOTTO_RESULT = "%d개 일치 (%d원) - %d개%s";
    private static final String VIEW_OF_PURCHASE_COUNT = "%d개를 구매했습니다.";
    private static final String VIEW_OF_INCOME_RATE = "총 수익률은 %.2f입니다.";
    private static final String VIEW_OF_LOTTO_STATISTICS_CONTENT = "%s%s%s";
    private static final String VIEW_OF_LOTTO_STATISTICS = "당첨 통계";
    private static final String VIEW_OF_SEPARATOR = "---------";
    
    
    public void printPurchasedLottos(int purchasingLottoCount, Lottos lottos) {
        printPurchasedCount(VIEW_OF_PURCHASE_COUNT, purchasingLottoCount);
        printPurchasedLottos(lottos);
    }
    
    private void printPurchasedCount(String viewOfPurchaseCount, double purchasingLottoCount2) {
        System.out.printf(viewOfPurchaseCount, purchasingLottoCount2);
    }
    
    private void printPurchasedLottos(Lottos lottos) {
        lottos.toInts()
              .forEach(lotto -> {
                  Collections.sort(lotto);
                  System.out.println(lotto);
              });
        
        System.out.println();
    }
    
    public void printStatistics(LottoStatisticResult result) {
        printStatisticsTitle();
        printStatisticsContent(result);
        printIncomeRate(result.calculateIncomeRate());
    }
    
    private void printStatisticsTitle() {
        System.out.printf(VIEW_OF_LOTTO_STATISTICS_CONTENT, System.lineSeparator(), VIEW_OF_LOTTO_STATISTICS,
                VIEW_OF_SEPARATOR);
    }
    
    private void printStatisticsContent(LottoStatisticResult result) {
        Arrays.stream(Rank.values())
              .sorted(RankComparator.INSTANCE)
              .forEach(rank -> System.out.printf(VIEW_OF_LOTTO_RESULT, rank.getMatchCount(), rank.getReward(),
                      result.get(rank), System.lineSeparator()));
    }
    
    private void printIncomeRate(double incomeRate) {
        System.out.printf(VIEW_OF_INCOME_RATE, incomeRate);
    }
}
