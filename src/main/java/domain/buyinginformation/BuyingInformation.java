package domain.buyinginformation;

import domain.lottonumbers.LottoTicket;

import java.util.List;

public class BuyingInformation {

    private Money buyingMoney;
    private List<LottoTicket> manualTickets;

    public BuyingInformation (Money buyingMoney, List<LottoTicket> manualTickets) {
        this.buyingMoney = buyingMoney;
        this.manualTickets = manualTickets;
    }

    public Money getMoneyAfterBuyingManualTickets() {
        return buyingMoney.getRemainedMoney(manualTickets.size());
    }

    public List<LottoTicket> getManualTickets() {
        return manualTickets;
    }
}
