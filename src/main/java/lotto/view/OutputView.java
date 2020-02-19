package lotto.view;

import lotto.domain.LottoTickets;

public class OutputView {
    public static void inputMoneyInstruction() {
        System.out.println("구입금액을 입력해 주세요.");
    }
    public static void ticketAmountInstruction(int quantity) {
        System.out.println(quantity + "개를 구매했습니다.");
    }
    public static void lottoTicketList(LottoTickets lottoTickets) {
        System.out.println(lottoTickets);
    }
    public static void inputWinningNumberInstruction() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }
    public static void inputBonusNumberInstruction() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }
    public static void prizeStatistics() {
        System.out.println("당첨 통계\n--------");
    }
}
