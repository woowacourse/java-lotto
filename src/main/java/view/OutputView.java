package view;

import controller.dto.LottoDto;
import controller.dto.LottosDto;
import controller.dto.StatisticDto;

import java.util.stream.Collectors;

import static view.InputView.LOTTO_NUMBER_DELIMITER;

public class OutputView {
    private static final String COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String STATISTIC_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String WINNING_MESSAGE = "%d개 일치 (%d원)- %d개\n";
    private static final String SECOND_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.2f입니다.\n";
    private static final String LOTTO_OPEN_FRAME = "[";
    private static final String LOTTO_CLOSE_FRAME = "]";

    private OutputView() {
    }

    public static void printCountOfLotto(int totalLottoCount, int manualLottoCount) {
        System.out.printf(COUNT_MESSAGE, manualLottoCount, totalLottoCount-manualLottoCount);
    }

    public static void printLottos(LottosDto lottosDto) {
        lottosDto.getLottos().forEach(OutputView::printLotto);
        System.out.println();
    }

    private static void printLotto(LottoDto lottoDto) {
        String LottoNumbers = lottoDto.getNumbers()
                .stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));

        System.out.println(LOTTO_OPEN_FRAME + LottoNumbers + LOTTO_CLOSE_FRAME);
    }

    public static void printStatistics(StatisticDto statistics) {
        System.out.println(STATISTIC_RESULT_MESSAGE);
        statistics.getStatisticDto()
                .entrySet()
                .stream()
                .filter(statistic -> statistic.getKey().getCount() != 0)
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