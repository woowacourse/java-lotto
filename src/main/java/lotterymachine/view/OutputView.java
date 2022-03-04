package lotterymachine.view;

import lotterymachine.LotteryPurchase;
import lotterymachine.domain.LotteryTicket;
import lotterymachine.domain.LotteryTickets;
import lotterymachine.domain.WinningLotteryRank;

import java.util.Map;

public class OutputView {
    private static final int PROFIT_STANDARD = 1;

    public static void printLotteryTickets(LotteryTickets lotteryTickets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LotteryTicket lotteryTicket: lotteryTickets.getLotteryTickets()) {
            stringBuilder.append(lotteryTicket.getNumbers()+"\n");
        }
        System.out.println(stringBuilder);
    }

    public static void printLotteryPurchase(LotteryPurchase lotteryPurchase) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", lotteryPurchase.getPassivityCount(), lotteryPurchase.getAutoCount());
    }

    public static void printLotteryPurchaseCount(int passivityCount, int autoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", passivityCount, autoCount);
    }
    public static void printWinningLotteryResults(Map<WinningLotteryRank, Integer> lotteryTicketResults) {
        System.out.println("당첨 통계\n" +
                "---------");
        for (WinningLotteryRank winningLotteryRank : lotteryTicketResults.keySet()) {
            printWinningLotteryResult(winningLotteryRank, lotteryTicketResults.get(winningLotteryRank));
        }
    }

    private static void printWinningLotteryResult(WinningLotteryRank winningLotteryRank, int number) {
        if (winningLotteryRank == WinningLotteryRank.BONUS_FIVE) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원)- %d개%n", winningLotteryRank.getNumber(), winningLotteryRank.getPrice(), number);
            return;
        }
        if (winningLotteryRank != WinningLotteryRank.ZERO) {
            System.out.printf("%d개 일치 (%d원)- %d개%n", winningLotteryRank.getNumber(), winningLotteryRank.getPrice(), number);
        }
    }

    public static void printProfitRate(double calculateProfitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", calculateProfitRate);
        System.out.printf("(기준이 1이기 때문에 결과적으로 %s라는 의미임)", extracted(calculateProfitRate));
    }

    private static String extracted(double calculateProfitRate) {
        if (calculateProfitRate < PROFIT_STANDARD) {
            return "손해";
        }
        return "이득";
    }
}