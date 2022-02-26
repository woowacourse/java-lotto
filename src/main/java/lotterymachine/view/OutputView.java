package lotterymachine.view;

import lotterymachine.model.LotteryTicket;
import lotterymachine.model.WinningLottery;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printNumberOfTicket(int number) {
        System.out.printf("%d개를 구매했습니다.%n", number);
    }

    public static void printLotteryTickets(List<LotteryTicket> lotteryTickets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LotteryTicket lotteryTicket : lotteryTickets) {
            stringBuilder.append(lotteryTicket.getNumbers().toString()).append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static void printWinningLotteryResults(Map<WinningLottery, Integer> lotteryTicketResults) {
        System.out.println("당첨 통계\n" +
                "---------");
        for (WinningLottery winningLottery: lotteryTicketResults.keySet()) {
            printWinningLotteryResult(winningLottery, lotteryTicketResults.get(winningLottery));
        }
    }

    private static void printWinningLotteryResult(WinningLottery winningLottery, int number) {
        if (winningLottery == WinningLottery.BONUS_FIVE) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원)- %d개%n", winningLottery.getNumber(), winningLottery.getPrice(), number);
        }
        if (winningLottery != WinningLottery.ZERO) {
            System.out.printf("%d개 일치 (%d원)- %d개%n", winningLottery.getNumber(), winningLottery.getPrice(), number);
        }
    }

    public static void printProfitRate(double calculateProfitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", calculateProfitRate);
        System.out.printf("(기준이 1이기 때문에 결과적으로 %s라는 의미임)", extracted(calculateProfitRate));
    }

    private static String extracted(double calculateProfitRate) {
        if (calculateProfitRate < 1) {
            return "손해";
        }
        return "이득";
    }
}
