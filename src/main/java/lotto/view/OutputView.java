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
}
