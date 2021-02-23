package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_LOTTO_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String RESULT_MESSAGE = "당첨 통계";
    private static final String HYPHENS = "---------";
    private static final String LOTTO_STATISTICS_MESSAGE = "%d개 일치 (%d원)- %d개\n";
    private static final String LOTTO_STATISTICS_BONUS_BALL_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    private static final String YIELD_MESSAGE = "총 수익률은 %.2f입니다.\n";
    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    private OutputView() {}

    public static void printLottoCountMessage(PurchasingCount purchasingCount) {
        System.out.printf(PURCHASE_LOTTO_MESSAGE, purchasingCount.getManualTicketCounts(), purchasingCount.getAutoTicketCounts());
    }

    public static void printLottoTicketNumbers(LottoTickets lottoTickets) {
        List<LottoTicket> lottoTicketGroup = lottoTickets.getLottoTicket();
        for (LottoTicket lottoTicket : lottoTicketGroup) {
            String numbers = lottoTicket.getLottoNumbers()
                    .stream()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getLottoNumber()))
                    .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
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
        int matchCounts = lottoRank.getMatchCounts();
        int prizeMoney = lottoRank.getPrizeMoney();
        long winningCounts = lottoStatistics.getCounts(lottoRank);
        if (lottoRank == LottoRank.SECOND) {
            System.out.printf(LOTTO_STATISTICS_BONUS_BALL_MESSAGE, matchCounts, prizeMoney, winningCounts);
            return;
        }
        System.out.printf(LOTTO_STATISTICS_MESSAGE, matchCounts, prizeMoney, winningCounts);
    }
}
