package view;

import domain.LottoPrize;
import domain.LottoTicket;
import domain.Profit;
import domain.WinningStatistics;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printLottoTickets(List<LottoTicket> lottoTickets) {
        System.out.printf("%d개를 구매했습니다.\n", lottoTickets.size());

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getNumbers());
        }
    }

    public static void printWinningStatistics(WinningStatistics winningStatistics) {
        Map<LottoPrize, Integer> prizeCounter = winningStatistics.getPrizeCounter();
        for (LottoPrize lottoPrize : Arrays.stream(LottoPrize.values())
                .sorted(Comparator.reverseOrder())
                .toList()) {
            if (lottoPrize.equals(LottoPrize.NOTHING)) {
                continue;
            }

            System.out.printf("%d개 일치%s(%d원)- %d개\n", lottoPrize.getCountMatched(),
                    printBonusNumberMatched(lottoPrize.isBonusNumberMatched()),
                    lottoPrize.getMoney(),
                    prizeCounter.get(lottoPrize));
        }
    }

    private static String printBonusNumberMatched(boolean isBonusNumberMatched) {
        if (isBonusNumberMatched) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    public static void printProfit(Profit profit) {
        System.out.printf("총 수익률은 %.2f입니다.", Math.floor(profit.getProfit() * 100.0) / 100.0);
    }
}
