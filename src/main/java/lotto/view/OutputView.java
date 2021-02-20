package lotto.view;

import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.statistics.LottoStatistics;
import lotto.domain.primitive.Money;

public class OutputView {

    private static final String ENTER = System.lineSeparator();
    private static final String BUY_LOTTO_MESSAGE = "%d개를 구매했습니다." + ENTER;
    private static final String WINNING_DETAIL_HEADER = ENTER + "당첨 통계" + ENTER + "---------";
    private static final String TOTAL_EARNING_RATE_MESSAGE = "총 수익률은 %.2f입니다.";
    private static final String NUMBER_DELIMITER = ",";
    private static final String LOTTO_RESULT_OPEN = "[";
    private static final String LOTTO_RESULT_CLOSE = "]";

    public static void getMessage(String message) {
        System.out.println(message);
    }

    public static void printBuyLotto(int count) {
        System.out.printf(BUY_LOTTO_MESSAGE, count);
    }

    public static void printLottoResults(LottoRepository lottoRepository) {
        StringBuilder log = new StringBuilder();
        for (Lotto lotto : lottoRepository.toList()) {
            printLottoResult(log, lotto);
        }
        log.append(ENTER);
        System.out.print(log.toString());
    }

    public static void printLottoResult(StringBuilder log, Lotto lotto) {
        log.append(LOTTO_RESULT_OPEN);
        String body = lotto.getNumbers().stream()
            .map(String::valueOf)
            .collect(Collectors.joining(NUMBER_DELIMITER));
        log.append(body);
        log.append(LOTTO_RESULT_CLOSE).append(ENTER);
    }

    public static void printWinningStats(LottoStatistics lottoStatistics, int money) {
        printWinningDetail(lottoStatistics);
        printEarningRate(lottoStatistics.getEarningRate(new Money(money)));
    }

    private static void printWinningDetail(LottoStatistics lottoStatistics) {
        System.out.println(WINNING_DETAIL_HEADER);
        System.out.print(lottoStatistics.getWinningDetail());
    }

    private static void printEarningRate(double rate) {
        System.out.printf(TOTAL_EARNING_RATE_MESSAGE, rate);
    }
}
