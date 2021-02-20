package lotto.view;

import lotto.domain.*;

import java.util.Arrays;

public class OutputView {
    
    private static final String VIEW_OF_LOTTO_RESULT = "%d개 일치 (%d원) - %d개%s";
    
    private static final String VIEW_OF_PURCHASE_COUNT = "%d개를 구매했습니다.%s";
    
    private static final String VIEW_OF_INCOME_RATE = "총 수익률은 %.2f입니다.";
    
    private static final String VIEW_OF_LOTTO_STATISTICS_TITLE_FORMAT = "%s%s%s%s%s";
    
    private static final String VIEW_OF_LOTTO_STATISTICS_TITLE = "당첨 통계";
    
    private static final String VIEW_OF_SEPARATOR = "---------";
    
    private static final String NEW_LINE = System.lineSeparator();
    
    public void printPurchaseLottos(PaymentAmount paymentAmount, Lottos lottos) {
        printPurchaseCount(paymentAmount);
        printPurchaseLottos(lottos);
    }
    
    private void printPurchaseCount(PaymentAmount paymentAmount) {
        System.out.printf(VIEW_OF_PURCHASE_COUNT, paymentAmount.getPurchaseCount(), NEW_LINE);
    }
    
    private void printPurchaseLottos(Lottos lottos) {
        lottos.toInts()
              .forEach(System.out::println);
        
        System.out.println();
    }
    
    public void printStatistic(LottoStatisticResult result) {
        printStatisticTitle();
        printStatisticContent(result);
        printIncomeRate(result.calculateIncomeRate());
    }
    
    private void printStatisticTitle() {
        System.out.printf(VIEW_OF_LOTTO_STATISTICS_TITLE_FORMAT, NEW_LINE, VIEW_OF_LOTTO_STATISTICS_TITLE, NEW_LINE,
                VIEW_OF_SEPARATOR, NEW_LINE);
    }
    
    private void printStatisticContent(LottoStatisticResult result) {
        Arrays.stream(Rank.values())
              .sorted()
              .forEach(rank -> System.out.printf(VIEW_OF_LOTTO_RESULT, rank.getMatchCount(), rank.getReward(),
                      result.get(rank), NEW_LINE));
    }
    
    private void printIncomeRate(double incomeRate) {
        System.out.printf(VIEW_OF_INCOME_RATE, incomeRate);
    }
}
