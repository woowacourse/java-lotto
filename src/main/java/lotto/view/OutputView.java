package lotto.view;

import static java.util.stream.Collectors.toList;

import java.util.List;
import lotto.domain.lotto.LottoGroup;
import lotto.domain.lotto.LottoNumbers;
import lotto.domain.lotto.AnalysedLottos;
import lotto.domain.rank.Rank;

public class OutputView {
    private static final String PAYOUT = "구입금액을 입력해 주세요";
    private static final String LOTTO_COUNT_FORMAT = "개를 구매했습니다.";
    private static final String LAST_WEEK_LOTTO_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요";
    private static final String BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS ="\n당첨 동계";
    private static final String LINE = "---------";
    private static final String STATISTICS_NONE_BONUS_FORMAT = "%d개 일치 (%d원)- %d개\n";
    private static final String STATISTICS_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    private static final String STATISTICS_YIELD_FORMAT = "총 수익률은 %.2f입니다.\n";
    private static final String LOTTO_NUMBERS_FORMAT = "[%s]\n";

    public static void payout() {
        System.out.println(PAYOUT);
    }

    public static void payOuted(int lottoCount) {
        System.out.println(String.valueOf(lottoCount).concat(LOTTO_COUNT_FORMAT));
    }

    public static void boughtLottos(LottoGroup lottoGroup) {
        lottoGroup.getLottos().forEach(OutputView::printLottoNumbers);
    }

    private static void printLottoNumbers(LottoNumbers lottoNumbers) {
        List<String> convertedLottoNumbers = lottoNumbers.toIntegerList().stream()
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

    public static void statistics(AnalysedLottos analysedLottos){
        System.out.println(WINNING_STATISTICS);
        System.out.println(LINE);
        analysedLottos.getRankings().forEach(OutputView::printStatisticsAccordingToBonus);
        System.out.printf(STATISTICS_YIELD_FORMAT, analysedLottos.getYield());
    }

    private static void printStatisticsAccordingToBonus(Rank rank) {
        String format = STATISTICS_NONE_BONUS_FORMAT;

        if(rank.getBonus()) {
           format = STATISTICS_BONUS_FORMAT;
        }

        System.out.printf(format, rank.getMatchingCount(), rank.getWinnings(), rank.getCount());
    }
}
