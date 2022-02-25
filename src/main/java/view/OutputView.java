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

    public static final String PROFIT_PATTERN = "#.##";
    public static final String BLANK = "";

    private OutputView() {
        throw new AssertionError();
    }

    public static void printPurchasedLottoTicketNumber(int number) {
        System.out.println(number + "개를 구매했습니다.");
    }

    public static void printPurchasedLottoTickets(List<LottoTicket> lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getTicketNumbers());
        }
        System.out.println();
    }

    public static void printWinningStat(WinningStat winningStat, double profit) {
        List<LottoRank> lottoRanks = LottoRank.valuesWithPrize();
        Collections.reverse(lottoRanks);

        System.out.println("\n당첨 통계");
        System.out.println("--------");
        System.out.print(createStatView(winningStat.getStat(), lottoRanks));
        System.out.println("총 수익률은 " + formatProfit(profit) + "입니다.");
    }

    private static String createStatView(Map<LottoRank, Integer> statistics, List<LottoRank> lottoRanks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoRank lottoRank : lottoRanks) {
            stringBuilder.append(lottoRank.getMatchNumber()).append("개 일치")
                    .append(checkSecond(lottoRank))
                    .append(" (").append(lottoRank.getPrize()).append(") - ")
                    .append(statistics.get(lottoRank)).append("개\n");
        }

        return stringBuilder.toString();
    }

    private static String checkSecond(LottoRank lottoRank) {
        if (LottoRank.SECOND == lottoRank) {
            return ", 보너스 볼 일치";
        }
        return BLANK;
    }

    private static String formatProfit(double profit) {
        DecimalFormat profitFormatter = new DecimalFormat(PROFIT_PATTERN);
        profitFormatter.setRoundingMode(RoundingMode.DOWN);
        return profitFormatter.format(profit);
    }
}
