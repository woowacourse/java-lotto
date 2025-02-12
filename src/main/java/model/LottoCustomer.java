package model;

import java.util.List;

public class LottoCustomer {

    private final List<LottoTicket> tickets;
    private final int purchaseAmount;

    public LottoCustomer(List<LottoTicket> tickets, int purchaseAmount) {
        this.tickets = tickets;
        this.purchaseAmount = purchaseAmount;
    }
}
