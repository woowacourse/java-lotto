package domain.lottostore;

import domain.lottonumber.LottoTicket;

import java.util.List;

public interface LottoStore<T> {

    public List<LottoTicket> generateTickets(T t);
}
