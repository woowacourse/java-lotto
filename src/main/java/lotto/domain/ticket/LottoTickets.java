package lotto.domain.ticket;

import java.util.List;

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
}
