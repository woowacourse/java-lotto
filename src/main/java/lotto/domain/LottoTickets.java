package lotto.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lottonumber.LottoTicket;

public class LottoTickets {

    private final List<LottoTicket> values;

    public LottoTickets(List<String> ticketStrings) {
        values = ticketStrings.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<LottoTicket> values() {
        return values;
    }

    public int size() {
        return values.size();
    }
}
