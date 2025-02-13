package lotto.view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import lotto.domain.LottoAward;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String TITLE_WINNING_RESULT = LINE + "당첨 통계" + LINE + "---------";
    private static final String FORMAT_WINNING_RESULT = "%d개 일치 (%d원)- %d개";

    public void printLottoCount(final int lottoCount) {
        System.out.printf("%d개를 구매했습니다." + LINE, lottoCount);
    }

    public void printWinningResult(final Map<LottoAward, Integer> winningResult) {
        System.out.println(TITLE_WINNING_RESULT);
        Arrays.stream(LottoAward.values())
                .sorted(Comparator.comparing(LottoAward::getMatchingCount))
                .filter(lottoAward -> !lottoAward.equals(LottoAward.NONE))
                .forEach(lottoAward -> System.out.printf(FORMAT_WINNING_RESULT + LINE, lottoAward.getMatchingCount(),
                        lottoAward.getAmount(), winningResult.get(lottoAward)));
    }

    public void printProfitRate(final double profitRate) {
        double flooredProfitRage = Math.floor(profitRate * 100) / 100;
        String message = "총 수익률은 %.2f입니다.";
        message += makeResultMessage(profitRate);
        System.out.printf(message, flooredProfitRage);
    }

    private String makeResultMessage(double profitRate) {
        if (profitRate >= 1) {
            return "(기준이 1이기 때문에 결과적으로 이익이라는 의미임)";
        }
        return "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    }
}
