package domain.lottostore;

import domain.lottonumbers.LottoTicket;

import java.util.List;

public interface LottoStore<T> {

    public List<LottoTicket> generateTickets(T t);

}
