package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_LOTTO_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String RESULT_MESSAGE = "당첨 통계";
    private static final String HYPHENS = "---------";
    private static final String LOTTO_STATISTICS_MESSAGE = "%d개 일치 (%d원)- %d개\n";
    private static final String LOTTO_STATISTICS_BONUS_BALL_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    private static final String YIELD_MESSAGE = "총 수익률은 %.2f입니다.\n";

    private OutputView() {}

    public static void printLottoCountMessage(int lottoCounts) {
        System.out.printf(PURCHASE_LOTTO_MESSAGE, lottoCounts);
    }

    public static void printLottoTicketNumbers(LottoTickets lottoTickets) {
        List<LottoTicket> lottoTicketGroup = lottoTickets.getLottoTicket();
        for (LottoTicket lottoTicket : lottoTicketGroup) {
            String numbers = lottoTicket.getLottoNumbers()
                    .stream()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getLottoNumber()))
                    .collect(Collectors.joining(", ", "[", "]"));
            System.out.println(numbers);
        }
        System.out.println();
    }

    public static void printLottoResult(LottoStatistics lottoStatistics, PurchasingPrice purchasingPrice) {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        System.out.println(HYPHENS);
        Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank != LottoRank.MISS)
                .sorted(Comparator.reverseOrder())
                .forEach(lottoRank -> printStatisticsMessage(lottoRank, lottoStatistics));
        System.out.printf(YIELD_MESSAGE, lottoStatistics.calculateYield(purchasingPrice));
    }

    private static void printStatisticsMessage(LottoRank lottoRank, LottoStatistics lottoStatistics) {
        if (lottoRank == LottoRank.SECOND) {
            System.out.printf(LOTTO_STATISTICS_BONUS_BALL_MESSAGE, lottoRank.getMatchCounts(), lottoRank.getPrizeMoney(),
                    lottoStatistics.getCounts(lottoRank));
            return;
        }
        System.out.printf(LOTTO_STATISTICS_MESSAGE, lottoRank.getMatchCounts(), lottoRank.getPrizeMoney(),
                lottoStatistics.getCounts(lottoRank));
    }
}
