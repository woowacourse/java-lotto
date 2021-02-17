package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoStats;

public class OutputView {

    private static final String ENTER = System.lineSeparator();
    private static final String BUY_LOTTO_MESSAGE = "%d개를 구매했습니다." + ENTER;
    private static final String WINNING_DETAIL_HEADER = "당첨 통계" + ENTER + "---------";
    private static final String TOTAL_EARNING_RATE_MESSAGE = "총 수익률은 %d입니다.";

    public static void getMessage(String message) {
        System.out.println(message);
    }

    public static void printBuyLotto(int count) {
        System.out.printf(BUY_LOTTO_MESSAGE, count);
    }

    public static void printLottoResults(List<Lotto> lottos) {
        StringBuilder log = new StringBuilder();
        for (Lotto lotto : lottos) {
            printLottoResult(log, lotto);
        }
        log.append(ENTER);
        System.out.print(log.toString());
    }

    public static void printLottoResult(StringBuilder log, Lotto lotto) {
        log.append("[");
        String body = lotto.getNumbers().stream().map(String::valueOf)
            .collect(Collectors.joining(","));
        log.append(body);
        log.append("]").append(ENTER);
    }

    public static void printWinningDetail(LottoStats lottoStats) {
        System.out.println(WINNING_DETAIL_HEADER);
        System.out.print(lottoStats.getWinningDetail());
    }

    public static void printEarningRate(int rate) {
        System.out.printf(TOTAL_EARNING_RATE_MESSAGE, rate);
    }
}
