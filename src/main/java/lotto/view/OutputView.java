package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class OutputView {
    public static final void printMoneyMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printTicketCountMessage(int counts) {
        System.out.println(counts + "개를 구매했습니다.");
    }

    public static void printAllTickets(LottoTickets lottoTickets) {
        lottoTickets.toList().forEach(OutputView::printTicket);
    }

    private static void printTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottoNumbers());
    }
}
