package lottogame.domain.machine;

import lottogame.domain.Money;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTickets;

public class LottoTicketIssueMachine {

    private static final int TICKET_PRICE = 1000;

    private LottoTicketIssueMachine() {
    }

    public static LottoTickets issueTickets(final Money money) {
        LottoTickets lottoTickets = new LottoTickets();
        while (money.isCanBuyAmount(TICKET_PRICE)) {
            money.spent(TICKET_PRICE);
            lottoTickets.add(new LottoTicket());
        }
        return lottoTickets;
    }
}
