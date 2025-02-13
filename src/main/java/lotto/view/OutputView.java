package lotto.view;

import lotto.domain.Lottos;

public class OutputView {

    private static final String RESULT_HEADER = "당첨 통계";
    private static final String RESULT_MULTI_DASH = "---------";
    private static final String PURCHASE_MESSAGE = "%d개를 구매했습니다.";
    private static final String PROFIT_MESSAGE = "총 수익률은 %.2f입니다.";

    public void printCount(final int count) {
        System.out.println(String.format(PURCHASE_MESSAGE, count));
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.toString());
    }

    public void printResult(String result) {
        System.out.println(System.lineSeparator() + RESULT_HEADER);
        System.out.println(RESULT_MULTI_DASH);
        System.out.println(result);
    }

    public void printProfitRate(double profitRate) {
        System.out.println(String.format(PROFIT_MESSAGE, profitRate));
    }

}
