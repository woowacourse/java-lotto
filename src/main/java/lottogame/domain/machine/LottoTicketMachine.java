package lottogame.domain.machine;

import lottogame.domain.Money;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTickets;

public class LottoTicketMachine {

    public static final int TICKET_PRICE = 1000;

    public LottoTickets buyAutoTickets(final Money money) {
        LottoTickets lottoTickets = new LottoTickets();

        while (money.isCanBuy(TICKET_PRICE)) {
            lottoTickets.add(LottoTicket.of());
            money.use(TICKET_PRICE);
        }
        return lottoTickets;
    }

    public LottoTicket buyManualTicket(final Money money, final String selectedLottoNumbers) {
        if (!money.isCanBuy(TICKET_PRICE)) {
            throw new IllegalArgumentException("남은 금액이 모자릅니다.");
        }
        money.use(TICKET_PRICE);
        return LottoTicket.of(new LottoNumbers(selectedLottoNumbers));
    }
}
