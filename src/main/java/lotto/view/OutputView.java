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
}
