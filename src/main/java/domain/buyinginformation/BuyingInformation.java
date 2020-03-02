package domain.buyinginformation;

import domain.lottonumbers.LottoTicket;

import java.util.List;

public class BuyingInformation {

    private Money buyingMoney;
    private List<LottoTicket> manualTickets;

    public BuyingInformation(Money buyingMoney, List<LottoTicket> manualTickets) {
        this.buyingMoney = buyingMoney;
        this.manualTickets = manualTickets;
    }

    public Money getMoneyAfterBuyingManualTickets() {
        return this.buyingMoney.getRemainedMoney(this.manualTickets.size());
    }

    public List<LottoTicket> getManualTickets() {
        return this.manualTickets;
    }

    public Money getBuyingMoney() {
        return this.buyingMoney;
    }
}
