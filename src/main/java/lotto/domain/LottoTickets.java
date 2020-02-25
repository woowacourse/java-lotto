package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets implements Iterable<LottoTicket> {
    private static final String DELIMITER = System.lineSeparator();

    private List<LottoTicket> lottoTickets;

    LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public static LottoTickets createLottoTickets(Money money, LottoTickets manualTickets) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0, end = money.ticketQuantity(); i < end; i++) {
            lottoTickets.add(LottoTicket.create());
        }
        for (LottoTicket manualTicket : manualTickets) {
            lottoTickets.add(manualTicket);
        }
        return new LottoTickets(lottoTickets);
    }

    public static LottoTickets createLottoTickets(List<LottoTicket> lottoTickets) {
        return new LottoTickets(lottoTickets);
    }

    @Override
    public String toString() {
        return lottoTickets.stream()
            .map(LottoTicket::toString)
            .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Iterator<LottoTicket> iterator() {
        return lottoTickets.iterator();
    }
}
