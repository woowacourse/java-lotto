package view;

import dto.LottoDto;
import java.util.List;

public class OutputView {

    private static final String PURCHASE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String WIN_STATISTICS_RESULT_MESSAGE = "당첨 통계\n---------";

    public void printLotto(final List<LottoDto> issuedLotto, final int manualCount) {
        System.out.println();
        System.out.printf(OutputView.PURCHASE_MESSAGE, manualCount, issuedLotto.size() - manualCount);
        System.out.println();
        for (LottoDto lotto : issuedLotto) {
            System.out.println(lotto.get());
        }
    }

    public void printWinStatistics(final String statisticsMessage) {
        System.out.println();
        System.out.println(WIN_STATISTICS_RESULT_MESSAGE);
        System.out.println(statisticsMessage);
    }

    public void printWinProfit(final String profitOrNotMessage) {
        System.out.println(profitOrNotMessage);
    }
}
