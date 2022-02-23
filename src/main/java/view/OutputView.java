package view;

import domain.LottoRank;
import domain.LottoTicket;
import domain.WinningStat;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String PURCHASED_NUMBER_MESSAGE = "개를 구매했습니다.";
    private static final String WINNING_STATISTICS = "\n당첨 통계";
    private static final String LINE = "--------";

    public static void printPurchasedLottoTicketNumber(int number) {
        System.out.println(number + PURCHASED_NUMBER_MESSAGE);
    }

    public static void printPurchasedLottoTickets(List<LottoTicket> lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getTicketNumbers());
        }
        System.out.println();
    }

    public static void printWinningStat(WinningStat winningStat) {
        List<LottoRank> lottoRanks = LottoRank.valuesWithoutNothing();
        Collections.reverse(lottoRanks);

        System.out.println(WINNING_STATISTICS);
        System.out.println(LINE);
        System.out.print(getStatistics(winningStat.getStat(), lottoRanks).toString());
        System.out.println("총 수익률은 " + formatProfit(winningStat) + "입니다.");
    }

    private static String formatProfit(WinningStat winningStat) {
        DecimalFormat profitFormatter = new DecimalFormat("#.##");
        profitFormatter.setRoundingMode(RoundingMode.DOWN);
        return profitFormatter.format(winningStat.calculateProfit(1000));
    }

    private static StringBuilder getStatistics(Map<LottoRank, Integer> statistics, List<LottoRank> lottoRanks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoRank lottoRank : lottoRanks) {
            stringBuilder.append(lottoRank.getMatchNumber()).append("개 일치")
                    .append(checkSecond(lottoRank))
                    .append(" (").append(lottoRank.getPrice()).append(") - ")
                    .append(statistics.get(lottoRank)).append("개\n");
        }
        return stringBuilder;
    }

    private static String checkSecond(LottoRank lottoRank) {
        if (LottoRank.SECOND == lottoRank) {
            return ", 보너스 볼 일치";
        }
        return "";
    }
}
