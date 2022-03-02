package lotto.view;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoRank;
import lotto.domain.vo.PurchaseAmount;
import lotto.domain.WinningStats;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;

public enum OutputView {

    INSTANCE;

    private static final String PURCHASED_TICKET_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String WINNING_STATS_MESSAGE = "당첨 통계\n---------";
    public static final String LOTTO_NUMBERS = "[%s]%n";
    public static final String WINNING_STATS_SECOND_RESULT_MESSAGE = "%s개 일치, 보너스 볼 일치 (%s원) - %s개%n";
    public static final String WINNING_STATS_RESULT_MESSAGE = "%s개 일치 (%s원) - %s개%n";
    public static final String EARNINGS_RESULT_MESSAGE = "총 수익률은 %.2f 입니다.";
    public static final String LOSS_WARNING_MESSAGE = "%s(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    public static final String LOTTO_NUMBER_DELIMITER = ", ";

    public void printPurchasedTickets(List<LottoTicket> lottoTickets, int manualTicketSize) {
        out.printf(PURCHASED_TICKET_MESSAGE, manualTicketSize, lottoTickets.size() - manualTicketSize);
        lottoTickets.forEach(this::printLottoNumbers);
    }

    private void printLottoNumbers(LottoTicket lottoTicket) {
        String lottoTicketString = lottoTicket.lottoNumbers().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));

        out.printf(LOTTO_NUMBERS, lottoTicketString);
    }

    public void printWinningStats(WinningStats winningStats, PurchaseAmount purchaseAmount) {
        out.println(WINNING_STATS_MESSAGE);
        List<LottoRank> targetLottoRanks = getLottoRanksToPrint();
        for (LottoRank lottoRank : targetLottoRanks) {
            printWinningResult(winningStats, lottoRank);
        }
        printEarningsResult(winningStats, purchaseAmount);
    }

    private void printEarningsResult(WinningStats winningStats, PurchaseAmount purchaseAmount) {
        double earningsRate = winningStats.getEarningsRate(purchaseAmount).doubleValue();
        String result = String.format(EARNINGS_RESULT_MESSAGE, earningsRate);
        if (earningsRate < 1) {
            result = String.format(LOSS_WARNING_MESSAGE, result);
        }
        out.println(result);
    }

    private void printWinningResult(WinningStats winningStats, LottoRank lottoRank) {
        if (lottoRank == LottoRank.THIRD) {
            out.printf(WINNING_STATS_SECOND_RESULT_MESSAGE,
                    LottoRank.THIRD.hitCount(),
                    LottoRank.THIRD.prizeMoney(),
                    winningStats.getCorrectAnswerNumbers(LottoRank.THIRD))
            ;
            return;
        }
        out.printf(WINNING_STATS_RESULT_MESSAGE,
                lottoRank.hitCount(),
                lottoRank.prizeMoney(),
                winningStats.getCorrectAnswerNumbers(lottoRank)
        );
    }

    private List<LottoRank> getLottoRanksToPrint() {
        return Arrays.stream(LottoRank.values())
                .filter((LottoRank lottoRank) -> lottoRank != LottoRank.NONE)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
