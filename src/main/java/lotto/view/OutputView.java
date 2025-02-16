package lotto.view;

import java.util.Comparator;
import java.util.Map;
import lotto.domain.LottoAward;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String BLANK = " ";
    private static final String TITLE_WINNING_RESULT = LINE + "당첨 통계" + LINE + "---------";
    private static final String TITLE_MATCHING_COUNT = "%d개 일치";
    private static final String TITLE_MATCHING_BONUS = ", 보너스 볼 일치";
    private static final String FORMAT_MATCHING_RESULT = "(%d원)- %d개";
    private static final int TRUNCATE_SCALE = 100;
    private static final int PROFIT_RATE_STANDARD = 1;

    public void printLottoCount(final int lottoCount) {
        System.out.printf("%d개를 구매했습니다." + LINE, lottoCount);
    }

    public void printWinningResult(final Map<LottoAward, Integer> winningResult) {
        System.out.println(TITLE_WINNING_RESULT);
        LottoAward.ACTUAL_LOTTO_AWARD.stream()
                .sorted(Comparator.comparing(LottoAward::getMatchingCount))
                .forEach(lottoAward -> System.out.printf(makeLottoAwardMessage(lottoAward),
                        lottoAward.getMatchingCount(), lottoAward.getAmount(), winningResult.get(lottoAward)));
    }

    public void printProfitRate(final double profitRate) {
        final double truncatedProfitRate = Math.floor(profitRate * TRUNCATE_SCALE) / TRUNCATE_SCALE;
        System.out.printf(makeResultMessage(profitRate), truncatedProfitRate);
    }

    private String makeLottoAwardMessage(final LottoAward lottoAward) {
        String message = TITLE_MATCHING_COUNT;
        if (lottoAward.equals(LottoAward.SECOND_RANK)) {
            message += TITLE_MATCHING_BONUS;
        }
        return message + BLANK + FORMAT_MATCHING_RESULT + LINE;
    }

    private String makeResultMessage(final double profitRate) {
        final String message = "총 수익률은 %.2f입니다.";
        if (profitRate >= PROFIT_RATE_STANDARD) {
            return message + "(기준이 1이기 때문에 결과적으로 이익이라는 의미임)";
        }
        return message + "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    }
}
