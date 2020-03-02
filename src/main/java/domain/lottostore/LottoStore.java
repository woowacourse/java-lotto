package domain.lottostore;

import domain.lottonumbers.LottoTicket;

import java.util.List;

public class LottoStore {

    public static List<LottoTicket> generateTickets(BuyingStrategy strategy, Object buyingInformation) {
        return strategy.generateTickets(buyingInformation);
    }
}
