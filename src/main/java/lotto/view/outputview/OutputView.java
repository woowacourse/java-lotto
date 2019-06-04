package lotto.view.outputview;

import lotto.domain.Rank;
import lotto.domain.WinningResult;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final int MINIMUM_WIN_COUNT = 3;
    private static final int MAXIMUM_WIN_COUNT = 6;
    private static final String NEXT_LINE = "\n";
    private static final String PURCHASE_AMOUNT_MESSAGE = "개를 구매했습니다.";
    private static final String RANK_STATISTICS_FORMAT = "%d개 일치 (%d원) - %d개\n";
    private static final String TOTAL_REVENUE_FORMAT = "총 수익률은 %.2f%% 입니다.";

    public static void printAmount(PurchaseAmount purchaseAmount) {
        System.out.println(purchaseAmount.getLottoAmount() + PURCHASE_AMOUNT_MESSAGE);
    }

    public static void printUserLottoTickets(UserTickets userTickets) {
        StringBuilder stringBuilder = new StringBuilder();

        for (LottoTicket lottoTicket : userTickets.getUserLottoTickets()) {
            stringBuilder.append(lottoTicket.getLottoTicket())
                    .append(NEXT_LINE);
        }

        System.out.println(stringBuilder.toString());
    }

    public static void printWinningStatistics(WinningResult winningResult, Integer purchasePrice) {
        StringBuilder stringBuilder = new StringBuilder();

        List<Rank> ranks = IntStream.rangeClosed(MINIMUM_WIN_COUNT, MAXIMUM_WIN_COUNT)
                .mapToObj(Rank::valueOf)
                .collect(Collectors.toList());

        for (Rank rank : ranks) {
            stringBuilder.append(String.format(RANK_STATISTICS_FORMAT, rank.getCountOfMatch()
                    , rank.getWinningMoney(), winningResult.getMatchedRankCountValue(rank)));
        }

        stringBuilder.append(String.format(TOTAL_REVENUE_FORMAT, winningResult.getTotalYield(purchasePrice)));
        System.out.println(stringBuilder.toString());
    }
}
