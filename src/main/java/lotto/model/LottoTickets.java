package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.utils.NumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    private LottoTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public static LottoTickets buy(NumberGenerator generator, Money money) {
        List<LottoTicket> tickets = IntStream.range(0, money.count())
            .mapToObj(x -> LottoTicket.createTicket(generator))
            .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
