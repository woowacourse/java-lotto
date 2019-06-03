package lotto.domain.ticket;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    List<LottoTicket>lottoTickets;
    public LottoTickets(final List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public LottoTicket getIdxLottoTicket(int order){
        return lottoTickets.get(order);
    }

    public int lottoTicketsSize(){
        return lottoTickets.size();
    }

    @Override
    public String toString() {
        return lottoTickets.stream().map(x -> x.toString()).collect(Collectors.joining("\n"));
    }
}
