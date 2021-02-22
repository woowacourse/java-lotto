package lotto.domain.ticketFactory;

import static lotto.domain.LottoNumber.LOTTO_NUMBER_MAX_LIMIT;
import static lotto.domain.LottoNumber.LOTTO_NUMBER_MIN_LIMIT;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class TicketBox {

    private final RandomTicketGenerator randomTicketGenerator;

    public TicketBox() {
        randomTicketGenerator = new RandomTicketGenerator();
    }

    public LottoTicket makeRandomTicket() {
        return new LottoTicket(randomTicketGenerator.generateNumbers());
    }
}
