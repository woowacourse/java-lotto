package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public static LottoTickets createLottoTickets(int buyingQuantity) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < buyingQuantity; i++) {
            lottoTickets.add(LottoTicket.create());
        }
        return new LottoTickets(lottoTickets);
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
            .map(LottoTicket::toString)
            .collect(Collectors.joining("\n"));
    }
}
