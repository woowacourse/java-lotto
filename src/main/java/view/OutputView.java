package view;

import domain.LottoPrize;
import domain.LottoTicket;
import domain.LottoTickets;
import domain.Profit;
import domain.WinningStatistics;
import domain.WinningStatisticsAndProfit;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printLottoTickets(LottoTickets lottoTickets) {
        System.out.printf("%d개를 구매했습니다.\n", lottoTickets.getSize());

        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            System.out.println(lottoTicket.getLottoNumbers().numbers());
        }
    }

    public static void printWinningStatisticsAndProfit(WinningStatisticsAndProfit winningStatisticsAndProfit) {
        printWinningStatistics(winningStatisticsAndProfit.winningStatistics());
        printProfit(winningStatisticsAndProfit.profit());
    }

    private static void printWinningStatistics(WinningStatistics winningStatistics) {
        Map<LottoPrize, Integer> prizeCounter = winningStatistics.getPrizeCounter();
        List<LottoPrize> lottoPrizes = Arrays.stream(LottoPrize.values())
                .filter(lottoPrize -> lottoPrize != LottoPrize.NOTHING)
                .sorted(Comparator.comparing((LottoPrize::getMoney)))
                .sorted(Comparator.reverseOrder())
                .toList();
        for (LottoPrize lottoPrize : lottoPrizes) {
            System.out.printf("%d개 일치%s(%d원)- %d개\n", lottoPrize.getCountMatched(),
                    printBonusNumberMatched(lottoPrize.getCountBonusNumberMatched()),
                    lottoPrize.getMoney(),
                    prizeCounter.get(lottoPrize));
        }
    }

    private static String printBonusNumberMatched(int countBonusNumberMatched) {
        if (countBonusNumberMatched == 1) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    private static void printProfit(Profit profit) {
        DecimalFormat df = new DecimalFormat("0.##");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.printf("총 수익률은 %s입니다.", df.format(profit.getProfit()));
    }
}
