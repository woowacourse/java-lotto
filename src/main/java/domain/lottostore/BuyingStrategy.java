package domain.lottostore;

import domain.buyinginformation.BuyingInformation;
import domain.lottonumbers.LottoTicket;

import java.util.List;

public interface BuyingStrategy<T> {

    public List<LottoTicket> generateTickets(T t);
}
