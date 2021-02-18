package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class OutputView {

    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printMoneyMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printTicketCountMessage(int counts) {
        System.out.println(counts + "개를 구매했습니다.");
    }

    public static void printAllTickets(LottoTickets lottoTickets) {
        lottoTickets.toList().forEach(OutputView::printTicket);
        printNewLine();
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요.");
    }

    public static void printWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
    }

    public static void printTotalWinningResult(float profit, String winningResult) {
        printNewLine();
        System.out.println("당첨 통계");
        printSplitLine();
        printProfit(profit);
        System.out.println(winningResult);
    }

    public static void printProfit(float profit) {
        System.out.printf("총 수익률은 %.2f입니다.%n", profit);
    }

    public static void printAboutChange(int change) {
        System.out.printf("거스름돈 %d원은 자선 단체에 기부되었습니다 \uD83D\uDE03%n", change);
    }

    private static void printSplitLine() {
        System.out.println("---------");
    }

    private static void printTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottoNumbers());
    }

}
