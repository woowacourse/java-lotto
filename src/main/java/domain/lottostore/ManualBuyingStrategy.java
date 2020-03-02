package domain.lottostore;

import domain.buyinginformation.BuyingInformation;
import domain.buyinginformation.Money;
import domain.lottonumbers.LottoTicket;

import java.util.ArrayList;
import java.util.List;

public class ManualBuyingStrategy implements BuyingStrategy<BuyingInformation>{

    @Override
    public List<LottoTicket> generateTickets(BuyingInformation buyingInformation) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.addAll(buyingInformation.getManualTickets());

        Money remainedMoney = buyingInformation.getMoneyAfterBuyingManualTickets();
        lottoTickets.addAll(new RandomBuyingStrategy().generateTickets(remainedMoney));

        return lottoTickets;
    }
}
