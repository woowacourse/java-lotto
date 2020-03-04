package domain.buyingstrategy;

import domain.buyingstrategy.buyinginformation.BuyingInformation;
import domain.buyingstrategy.buyinginformation.Money;
import domain.lottonumbers.LottoTicket;

import java.util.List;

public class ManualBuyingStrategy implements BuyingStrategy {

    @Override
    public boolean isAvailable(BuyingInformation buyingInformation) {
        List<LottoTicket> ticketsToBuy = buyingInformation.getManualTickets();
        Money moneyToBuy = buyingInformation.getBuyingMoney();

        return ticketsToBuy.size() <= moneyToBuy.getNumberOfTickets();
    }

    @Override
    public List<LottoTicket> generateTickets(BuyingInformation buyingInformation) {
        return buyingInformation.getManualTickets();
    }
}
