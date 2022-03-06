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

    public static LottoTickets buyAutoTicket(NumberGenerator generator, Money money) {
        List<LottoTicket> tickets = IntStream.range(0, money.count())
            .mapToObj(x -> LottoTicket.createRandomTicket(generator))
            .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }

    public LottoStatistics findLottoWinners(WinningTicket winningTicket) {
        return new LottoStatistics(tickets.stream()
            .map(winningTicket::compare)
            .collect(Collectors.toList()));
    }

    public void generatorManualTickets(List<String> manualLottoTickets) {
        for (String manualLottoTicket : manualLottoTickets) {
            tickets.add(LottoTicket.createTicket(manualLottoTicket));
        }
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
