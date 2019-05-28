package lotto.model;

import java.util.List;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public void add(LottoTickets lottoTickets) {
        this.lottoTickets.addAll(lottoTickets.getLottoTickets());
    }

    private List<LottoTicket> getLottoTickets() {
        return this.lottoTickets;
    }

    @Override
    public String toString(){
        return lottoTickets.stream()
                .map(lottoTicket -> new StringBuilder(lottoTicket.toString()))
                .reduce(new StringBuilder(), (previous, next) -> previous.append(next).append("\n"))
                .toString();
    }
}