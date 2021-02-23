package lottogame.domain.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lottogame.domain.Money;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.ticket.LottoTicket;
import lottogame.domain.ticket.LottoTickets;

public class LottoTicketIssueMachine {

    private static final int TICKET_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final Money money;

    public LottoTicketIssueMachine(final Money money) {
        this.money = money;
        validateMinPurchaseAmount(this.money);
    }

    public static int getTicketPrice() {
        return TICKET_PRICE;
    }

    private void validateMinPurchaseAmount(final Money money) {
        if (!money.canBuyAmount(TICKET_PRICE)) {
            throw new IllegalArgumentException("최소 입력 금액은 " + TICKET_PRICE + "원 이상입니다.");
        }
    }

    public LottoTickets issueTickets() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (this.money.canBuyAmount(TICKET_PRICE)) {
            this.money.spent(TICKET_PRICE);
            lottoTickets.add(new LottoTicket(issueNumbers()));
        }
        return new LottoTickets(lottoTickets);
    }

    private Set<LottoNumber> issueNumbers() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            lottoNumbers.add(new LottoNumber());
        }
        return lottoNumbers;
    }
}
