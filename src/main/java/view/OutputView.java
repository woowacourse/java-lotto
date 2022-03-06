package view;

import controller.dto.LottoDto;
import controller.dto.LottosDto;
import controller.dto.StatisticDto;

import java.util.stream.Collectors;

public class OutputView {

    private static final int ZERO = 0;
    private static final String COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String STATISTIC_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String WINNING_MESSAGE = "%d개 일치 (%d원)- %d개\n";
    private static final String SECOND_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.2f입니다.\n";
    private static final String DELIMITER = ", ";
    private static final String OPEN_BRACKETS = "[";
    private static final String CLOSE_BRACKETS = "]";

    private OutputView() {
    }

    public static void printCountOfLotto(int autoLottoCount, int manualLottoCount) {
        System.out.printf(COUNT_MESSAGE, manualLottoCount, autoLottoCount);
    }

    public static void printLottos(LottosDto lottosDto) {
        for (LottoDto lottoDto : lottosDto.getLottos()) {
            printLotto(lottoDto);
        }
        System.out.println();
    }

    private static void printLotto(LottoDto lottoDto) {
        String LottoNumbers = lottoDto.getNumbers()
                .stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(OPEN_BRACKETS + LottoNumbers + CLOSE_BRACKETS);
    }

    public static void printStatistics(StatisticDto statistics) {
        System.out.println(STATISTIC_RESULT_MESSAGE);
        statistics.getStatistic()
                .entrySet()
                .stream()
                .filter(statistic -> statistic.getKey().getCount() != ZERO)
                .forEach(statistic -> {
                    if (statistic.getKey().hasBonusBall()) {
                        System.out.printf(SECOND_MESSAGE, statistic.getKey().getCount(), statistic.getKey().getWinningPrice(), statistic.getValue());
                        return;
                    }
                    System.out.printf(WINNING_MESSAGE, statistic.getKey().getCount(), statistic.getKey().getWinningPrice(), statistic.getValue());
                });
    }

    public static void printProfitRate(double profitRate) {
        System.out.printf(PROFIT_RATE_MESSAGE, profitRate);
    }
}