package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets of(List<LottoTicket> lottoTickets) {
        return new LottoTickets(lottoTickets);
    }

    public void add(LottoTicket lottoTicket) {
        this.lottoTickets.add(lottoTicket);
    }

    public void addAll(LottoTickets lottoTickets) {
        this.lottoTickets.addAll(lottoTickets.lottoTickets());
    }

    public int size() {
        return this.lottoTickets.size();
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(this.lottoTickets);
    }
}
