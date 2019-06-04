package lotto.view.outputview;

import com.google.common.collect.Lists;
import lotto.domain.Rank;
import lotto.domain.WinningResult;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;
import lotto.utils.NullCheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEXT_LINE = "\n";
    private static final String PURCHASE_AMOUNT_MESSAGE = "개를 구매했습니다.";
    private static final String STATISTICS_MESSAGE_FORMAT_START = "%d개 일치";
    private static final String STATISTICS_MESSAGE_FORMAT_MIDDLE = " (%d원)";
    private static final String STATISTICS_MESSAGE_FORMAT_END = " - %d개\n";
    private static final String STATISTICS_MESSAGE_FORMAT_SECOND = ", 보너스 볼 일치";
    private static final String TOTAL_REVENUE_FORMAT = "총 수익률은 %.2f%% 입니다.";
    private static final String RESULT_STATISTICS_MESSAGE = "당첨 통계\n";
    private static final int LOSE = 0;
    private static final String EMPTY = "";

    private static StringBuilder stringBuilder;

    public static void printAmount(PurchaseAmount purchaseAmount) {
        NullCheckUtil.checkNullPurchaseAmount(purchaseAmount);
        
        stringBuilder = new StringBuilder();
        stringBuilder.append(purchaseAmount.getLottoAmount())
                .append(PURCHASE_AMOUNT_MESSAGE);

        System.out.println(stringBuilder.toString());
    }

    public static void printUserLottoTickets(UserTickets userTickets) {
        NullCheckUtil.checkNullUserTickets(userTickets);
        stringBuilder = new StringBuilder();

        for (LottoTicket lottoTicket : userTickets.getUserLottoTickets()) {
            stringBuilder.append(lottoTicket.getLottoTicket())
                    .append(NEXT_LINE);
        }

        System.out.println(stringBuilder.toString());
    }

    public static void printWinningStatistics(WinningResult winningResult, Integer purchasePrice) {
        NullCheckUtil.checkNullWinningResult(winningResult);
        NullCheckUtil.checkNullInteger(purchasePrice);

        stringBuilder = new StringBuilder();
        stringBuilder.append(RESULT_STATISTICS_MESSAGE);

        writeRankStatistics(winningResult);
        writeTotalYield(winningResult, purchasePrice);

        System.out.println(stringBuilder.toString());
    }

    private static void writeRankStatistics(WinningResult winningResult) {
        List<Rank> ranks = Lists.reverse(Arrays.stream(Rank.values()).collect(Collectors.toList()));
        ranks.remove(LOSE);

        for (Rank rank : ranks) {
            stringBuilder.append(getStartRankStatistics(rank));
            stringBuilder.append(getMessageIfSecondRank(rank));
            stringBuilder.append(getMiddleRankStatistics(rank));
            stringBuilder.append(getEndRankStatistics(winningResult, rank));
        }
    }

    private static String getMessageIfSecondRank(Rank rank) {
        if (rank.equals(Rank.SECOND)) {
            return STATISTICS_MESSAGE_FORMAT_SECOND;
        }

        return EMPTY;
    }

    private static String getStartRankStatistics(Rank rank) {
        return String.format(STATISTICS_MESSAGE_FORMAT_START, rank.getCountOfMatch());
    }

    private static String getMiddleRankStatistics(Rank rank) {
        return String.format(STATISTICS_MESSAGE_FORMAT_MIDDLE, rank.getWinningMoney());
    }


    private static String getEndRankStatistics(WinningResult winningResult, Rank rank) {
        return String.format(STATISTICS_MESSAGE_FORMAT_END, winningResult.getMatchedRankCountValue(rank));
    }

    private static void writeTotalYield(WinningResult winningResult, Integer purchasePrice) {
        stringBuilder.append(String.format(TOTAL_REVENUE_FORMAT, winningResult.getTotalYield(purchasePrice)));
    }
}
