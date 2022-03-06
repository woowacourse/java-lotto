package old.dto;

import java.util.List;

import old.domain.LottoTicket;
import old.domain.Money;

public class PurchaseResult {

    private final Money money;
    private final List<LottoTicket> tickets;

    public PurchaseResult(Money money, List<LottoTicket> tickets) {
        this.money = money;
        this.tickets = tickets;
    }

    public Money getMoney() {
        return money;
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }
}
