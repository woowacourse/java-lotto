package lotto.view;

import lotto.domain.LottoResult_VO;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;

public class ConsoleOutPutView {
    private static final String RESULT_MESSAGE = "%d개 일치 (%d원) - %d개\n";
    private static final String RESULT_SECOND_MESSAGE = "%d개 일치, 보너스볼 일치 (%d원) - %d개\n";
    public static final String NEW_LINE = "\n";

    public static void showLottoTicket(LottoTicket lottoTicket) {
        System.out.println(NEW_LINE + "수동으로 "
                + lottoTicket.getNumberOfCustomLotto()
                + "장, 자동으로 "
                + lottoTicket.getNumberOfAutoLotto()
                + "장을 구매 했습니다.");
        System.out.println(lottoTicket + NEW_LINE);
    }

    public static void showLottoResult(LottoResult_VO lottoResult) {
        System.out.println(NEW_LINE + "당첨 통계");
        System.out.println("--------------------");

        lottoResult.getResultKey()
                .stream()
                .forEach(rank -> printStatistics(rank, lottoResult));

        System.out.println(NEW_LINE + "총 수익률은 "
                + String.format("%.1f", lottoResult.dividendRate())
                + "% 입니다.");
    }

    private static void printStatistics(Rank rank, LottoResult_VO lottoResult) {
        if (rank == Rank.MISS) {
            return;
        }
        System.out.printf(rank != Rank.SECOND ? RESULT_MESSAGE : RESULT_SECOND_MESSAGE
                , rank.getCountOfMatch(), rank.getWinningMoney()
                , lottoResult.getResultValue(rank));
    }

}
