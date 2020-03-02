package domain.buyinginformation;

import domain.lottonumbers.LottoTicket;

import java.util.List;

public class BuyingInformation {

    private Money buyingMoney;
    private List<LottoTicket> manualTickets;

    BuyingInformation (Money buyingMoney) {
        this(buyingMoney, null);
    }

    BuyingInformation (Money buyingMoney, List<LottoTicket> manualTickets) {
        this.buyingMoney = buyingMoney;
        this.manualTickets = manualTickets;
    }

    public Money getBuyingMoney() {
        return buyingMoney;
    }

    public List<LottoTicket> getManualTickets() {
        return manualTickets;
    }
}
