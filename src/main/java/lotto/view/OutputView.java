package lotto.view;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.primitive.Money;
import lotto.domain.statistics.LottoStatistics;

import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    public static void getMessage(String message) {
        System.out.println(message);
    }

    public static void printBuyLotto(int count) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, count);
    }

    public static void printLottoResults(LottoRepository lottoRepository) {
        StringBuilder log = new StringBuilder();
        for (Lotto lotto : lottoRepository.toList()) {
            printLottoResult(log, lotto);
        }
        log.append(NEW_LINE);
        System.out.print(log.toString());
    }

    public static void printLottoResult(StringBuilder log, Lotto lotto) {
        log.append("[");
        String body = lotto.getNumbers()
                           .stream()
                           .map(String::valueOf)
                           .collect(Collectors.joining(","));
        log.append(body);
        log.append("]")
           .append(NEW_LINE);
    }

    public static void printWinningStats(LottoStatistics lottoStatistics, int money) {
        printWinningDetail(lottoStatistics);
        printEarningRate(lottoStatistics.getEarningRate(new Money(money)));
    }

    private static void printWinningDetail(LottoStatistics lottoStatistics) {
        System.out.println(NEW_LINE + "당첨 통계" + NEW_LINE + "---------");
        System.out.print(lottoStatistics.getWinningDetail());
    }

    private static void printEarningRate(double rate) {
        System.out.printf("총 수익률은 %.2f입니다.", rate);
    }
}
