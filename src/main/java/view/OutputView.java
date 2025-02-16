package view;

import domain.Lotto;
import domain.Rank;
import domain.Ticket;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String PURCHASE_RESULT = "%s개를 구매헀습니다.";
    private static final String WINNING_STATISTIC_MESSAGE = "당첨 통계";
    private static final String WINNING_RESULT = "%s개";
    private static final String RATE_MESSAGE = "총 수익률은 %,.2f입니다.";

    private OutputView() {
    }

    public static OutputView create() {
        return new OutputView();
    }

    public void printPurchaseResult(Ticket ticket) {
        System.out.printf(PURCHASE_RESULT, ticket.getQuantity());
        changeLine();
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningStatistic(Map<Rank, Integer> result) {
        printMessage(WINNING_STATISTIC_MESSAGE);
        printMessage("-".repeat(9));

        for (Rank rank : result.keySet()) {
            if (!rank.getDescription().isBlank()) {
                System.out.print(rank.getDescription() + " - ");
                System.out.printf(WINNING_RESULT, result.get(rank));
                changeLine();
            }
        }
    }

    public void printProfit(double profit) {
        System.out.printf(RATE_MESSAGE, profit);
        changeLine();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void changeLine() {
        System.out.println();
    }
}
