package lottogame.domain.machine;

import java.util.HashSet;
import java.util.Set;
import lottogame.domain.Money;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTickets;

public class LottoTicketIssueMachine {

    private static final int TICKET_PRICE = 1000;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private LottoTicketIssueMachine() {
    }

    public static LottoTickets issueTickets(final Money money) {
        LottoTickets lottoTickets = new LottoTickets();
        while (money.canBuyAmount(TICKET_PRICE)) {
            money.spent(TICKET_PRICE);
            lottoTickets.add(new LottoTicket(issueNumbers()));
        }
        return lottoTickets;
    }

    private static Set<LottoNumber> issueNumbers() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            lottoNumbers.add(new LottoNumber());
        }
        return lottoNumbers;
    }
}
