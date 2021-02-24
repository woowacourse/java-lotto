package lotto.view;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lotto.domain.lotto.LottoNumbers;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;

public class OutputView {

    private static final String PAYOUT = "구입금액을 입력해 주세요";
    private static final String LOTTO_COUNT_FORMAT = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String LAST_WEEK_LOTTO_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요";
    private static final String BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS = "\n당첨 통계";
    private static final String LINE = "---------";
    private static final String STATISTICS_NONE_BONUS_FORMAT = "%d개 일치 (%d원)- %d개\n";
    private static final String STATISTICS_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    private static final String STATISTICS_YIELD_FORMAT = "총 수익률은 %.2f입니다.\n";
    private static final String LOTTO_NUMBERS_FORMAT = "[%s]\n";
    private static final String MANUAL_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL = "\n수동으로 구매할 번호를 입력해 주세요.";

    public static void payout() {
        System.out.println(PAYOUT);
    }

    public static void payOuted(int manualCount, int autoCount) {
        System.out.printf(LOTTO_COUNT_FORMAT, manualCount, autoCount);
    }

    public static void boughtLotties(LottoTicket lottoTicket) {
        lottoTicket.unwrap().forEach(OutputView::printLottoNumbers);
    }

    private static void printLottoNumbers(LottoNumbers lottoNumbers) {
        List<String> convertedLottoNumbers = lottoNumbers.unwrap().stream()
            .map(String::valueOf)
            .collect(toList());

        System.out.printf(LOTTO_NUMBERS_FORMAT, String.join(", ", convertedLottoNumbers));
    }

    public static void lastWeekLottoNumber() {
        System.out.println(LAST_WEEK_LOTTO_NUMBER);
    }

    public static void bonusNumber() {
        System.out.println(BONUS_NUMBER);
    }

    public static void statistics(Ranks ranks) {
        printTitleOfStatistics();
        Map<Rank, Long> gameResult = ranks.unwrap();
        List<Rank> keys = new ArrayList<>(gameResult.keySet());

        keys.stream()
            .filter(rank -> rank != Rank.FAIL)
            .sorted(Comparator.comparingInt(Rank::getWinnings))
            .forEach(rank -> printStatisticsAccordingToBonus(rank, gameResult.get(rank)));
    }

    private static void printTitleOfStatistics() {
        System.out.println(WINNING_STATISTICS);
        System.out.println(LINE);
    }

    private static void printStatisticsAccordingToBonus(Rank rank, Long count) {
        String format = STATISTICS_NONE_BONUS_FORMAT;

        if (rank.isBonusNumber()) {
            format = STATISTICS_BONUS_FORMAT;
        }

        System.out.printf(format, rank.getMatchedNumber(), rank.getWinnings(), count);
    }

    public static void printYield(double yield) {
        System.out.printf(STATISTICS_YIELD_FORMAT, yield);
    }

    public static void manualCount() {
        System.out.println(MANUAL_COUNT);
    }

    public static void manual() {
        System.out.println(MANUAL);
    }
}
