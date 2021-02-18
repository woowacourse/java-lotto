package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class OutputView {

    public static final void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static final void printMoneyMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printTicketCountMessage(int counts) {
        System.out.println(counts + "개를 구매했습니다.");
    }

    public static void printAllTickets(LottoTickets lottoTickets) {
        lottoTickets.toList().forEach(OutputView::printTicket);
    }

    public static void printBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요.");
    }

    public static void printWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
    }

    public static void printWinningResultTitle() {
        System.out.println("당첨 통계");
        printSplitLine();
    }

    public static void printProfit(float profit) {
        System.out.println(String.format("총 수익률은 %.2f입니다.", profit));
    }

    private static void printSplitLine() {
        System.out.println("---------");
    }

    private static void printTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottoNumbers());
    }

}
