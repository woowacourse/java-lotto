package lotto.view;

import lotto.domain.*;

import java.util.Map;

public class OutputView {
    private static final String PURCHASE_INFO_MESSAGE = "수동으로 %d장, 자동으로 %d장을 구매했습니다.";
    private static final String WINNING_INFO_MESSAGE = "%s (%s)원 - %s개";
    private static final String WINNING_RESULT_MESSAGE = "%d개 일치";
    private static final String CONTAIN_BONUS_BALL = ", 보너스 볼 일치";
    private static final int NO_MATCH = 0;

    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
        printNewLine();
    }

    public static void printAllTickets(Purchase purchase, LottoTickets lottoTickets) {
        System.out.printf(PURCHASE_INFO_MESSAGE, purchase.getManualPurchase(), purchase.getAutoPurchase());
        printNewLine();
        lottoTickets.toList().forEach(OutputView::printTicket);
        printNewLine();
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printTotalWinningResult(Map<Rank, Integer> winningResult) {
        System.out.println("당첨 통계");
        printSplitLine();

        for (Rank result : Rank.values()) {
            printWinningResult(result, winningResult);
        }
    }

    public static void printProfit(LottoProfit profit) {
        printNewLine();
        System.out.printf("총 수익률은 %.2f입니다.%n", profit.getProfit());
    }

    public static void printAboutChange(int change) {
        printNewLine();
        System.out.printf("거스름돈 %d원은 자선 단체에 기부되었습니다 \uD83D\uDE03%n", change);
    }

    private static void printSplitLine() {
        System.out.println("---------");
    }

    private static void printTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.toSet());
    }

    private static void printWinningResult(Rank result, Map<Rank, Integer> winningResult) {
        if (result.getMatchCount() == NO_MATCH) {
            return;
        }

        System.out.printf(
                (WINNING_INFO_MESSAGE) + "%n",
                getWinningResultMessage(result),
                result.getWinnings(),
                convertCountNullToZero(winningResult.get(result)));
    }

    private static String getWinningResultMessage(Rank rank) {
        if (rank.equals(Rank.SECOND_PRIZE)) { // 보너스볼이 포함된 경우
            return String.format(WINNING_RESULT_MESSAGE + CONTAIN_BONUS_BALL, rank.getMatchCount());
        }
        return String.format(WINNING_RESULT_MESSAGE, rank.getMatchCount());
    }

    private static int convertCountNullToZero(Integer number) {
        if (number == null) {
            return 0;
        }
        return number;
    }
}
