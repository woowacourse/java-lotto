package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.utils.NumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public static LottoTickets buyTicket(NumberGenerator generator, int count) {
        List<LottoTicket> tickets = IntStream.range(0, count)
            .mapToObj(x -> LottoTicket.createTicket(generator))
            .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
