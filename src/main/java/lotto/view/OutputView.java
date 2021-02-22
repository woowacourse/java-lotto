package lotto.view;

import static java.util.stream.Collectors.toList;

import java.util.List;

import lotto.domain.lotto.LottoGroup;
import lotto.domain.lotto.LottoNumbers;
import lotto.domain.rank.Rank;

public class OutputView {
    private static final String PAYOUT = "구입금액을 입력해 주세요";
    private static final String MANUAL_PURCHASE = System.lineSeparator() + "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBER = System.lineSeparator() + "수동으로 구매할 번호를 입력해 주세요";
    private static final String LOTTO_COUNT_FORMAT = System.lineSeparator() + "수동으로 %d장, 자동으로 %d개를 구매했습니다." + System.lineSeparator();
    private static final String LAST_WEEK_LOTTO_NUMBER = System.lineSeparator() + "지난 주 당첨 번호를 입력해 주세요";
    private static final String BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS = System.lineSeparator() + "당첨 동계";
    private static final String LINE = "---------";
    private static final String STATISTICS_NONE_BONUS_FORMAT = "%d개 일치 (%d원)- %d개" + System.lineSeparator();
    private static final String STATISTICS_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원)- %d개" + System.lineSeparator();
    private static final String STATISTICS_YIELD_FORMAT = "총 수익률은 %.2f입니다." + System.lineSeparator();
    private static final String LOTTO_NUMBERS_FORMAT = "[%s]" + System.lineSeparator();

    private static final String LOTTO_NUMBERS_FORMAT_DELIMITER = ", ";

    public static void payout() {
        System.out.println(PAYOUT);
    }

    public static void manualPurchase() {
        System.out.println(MANUAL_PURCHASE);
    }

    public static void manualLottoNumber() {
        System.out.println(MANUAL_LOTTO_NUMBER);
    }

    public static void payOuted(int buyableLottoCount, int manualLottoCount) {
        System.out.printf(LOTTO_COUNT_FORMAT, manualLottoCount, buyableLottoCount - manualLottoCount);
    }

    public static void boughtLottos(LottoGroup lottoGroup) {
        lottoGroup.getLottos().forEach(OutputView::printLottoNumbers);
    }

    private static void printLottoNumbers(LottoNumbers lottoNumbers) {
        List<String> convertedLottoNumbers = lottoNumbers.getValueAsIntegerList().stream()
                .map(String::valueOf)
                .collect(toList());

        System.out.printf(LOTTO_NUMBERS_FORMAT,
                String.join(LOTTO_NUMBERS_FORMAT_DELIMITER, convertedLottoNumbers)
        );
    }

    public static void lastWeekLottoNumber() {
        System.out.println(LAST_WEEK_LOTTO_NUMBER);
    }

    public static void bonusNumber() {
        System.out.println(BONUS_NUMBER);
    }

    public static void printWinningStatistics(List<Rank> ranks) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(LINE);
        ranks.forEach(OutputView::printStatisticsAccordingToBonus);
    }

    public static void printYield(double yield) {
        System.out.printf(STATISTICS_YIELD_FORMAT, yield);
    }

    private static void printStatisticsAccordingToBonus(Rank rank) {
        if (rank.hasBonus()) {
            System.out.printf(STATISTICS_BONUS_FORMAT, rank.getMatchingCount(), rank.getWinnings(), rank.getCount());
            return;
        }

        System.out.printf(STATISTICS_NONE_BONUS_FORMAT, rank.getMatchingCount(), rank.getWinnings(), rank.getCount());
    }
}
