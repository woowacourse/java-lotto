package lottogame.domain.machine;

import lottogame.domain.Money;
import lottogame.domain.ticket.LottoAutoTicket;
import lottogame.domain.ticket.LottoTickets;

public class LottoTicketMachine {

    public static final int TICKET_PRICE = 1000;

    public LottoTickets buyTickets(final Money money) {
        LottoTickets lottoTickets = new LottoTickets();

        while (money.isCanBuy(TICKET_PRICE)) {
            lottoTickets.add(new LottoAutoTicket());
            money.use(TICKET_PRICE);
        }
        return lottoTickets;
    }
}
