package lotterymachine.view;

import lotterymachine.model.LotteryTicket;

import java.util.List;

public class OutputView {

    public static void printNumberOfTicket(int number) {
        System.out.printf("%d개를 구매했습니다.%n", number);
    }

    public static void printLotteryTickets(List<LotteryTicket> lotteryTickets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (LotteryTicket lotteryTicket: lotteryTickets) {
            stringBuilder.append(lotteryTicket.getNumbers().toString()).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
