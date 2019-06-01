package lotto.model.lotto;

import java.util.List;
import java.util.stream.Stream;

public class LottoTickets implements Cloneable {
    private List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets from(List<LottoTicket> lottoTickets) {
        return new LottoTickets(lottoTickets);
    }

    public void addAll(LottoTickets lottoTickets) {
        this.lottoTickets.addAll(lottoTickets.lottoTickets);
    }

    public LottoTickets copy() {
        return new LottoTickets(lottoTickets);
    }

    public Stream<LottoTicket> stream() {
        return lottoTickets.stream();
    }

    public int size() {
        return lottoTickets.size();
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
                .map(lottoTicket -> new StringBuilder(lottoTicket.toString()))
                .reduce(new StringBuilder(), (previous, next) -> previous.append(next).append("\n"))
                .toString();
    }
}