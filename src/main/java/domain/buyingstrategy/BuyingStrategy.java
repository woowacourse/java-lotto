package domain.buyingstrategy;

import domain.buyingstrategy.buyinginformation.BuyingInformation;
import domain.lottonumbers.LottoTicket;

import java.util.List;

public interface BuyingStrategy {

    public boolean isAvailable(BuyingInformation buyingInformation);
    public List<LottoTicket> generateTickets(BuyingInformation buyingInformation);
}
