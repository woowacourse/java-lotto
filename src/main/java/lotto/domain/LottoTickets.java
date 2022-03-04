package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public static LottoTickets buy(NumberGenerator generator, Money money) {
        List<LottoTicket> tickets = IntStream.range(0, money.count())
            .mapToObj(x -> LottoTicket.createTicket(generator))
            .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }

    public LottoStatistics findLottoWinners(WinningTicket winningTicket) {
        List<LottoRank> wins = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            LottoRank rank = winningTicket.compare(ticket);
            wins.add(rank);
        }
        return new LottoStatistics(wins);
    }

    public void generatorManualTickets(List<String> manualLottoTickets) {
        for (String manualLottoTicket : manualLottoTickets) {
            tickets.add(LottoTicket.createTicket(manualLottoTicket));
        }
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTickets that = (LottoTickets) o;
        return Objects.equals(getTickets(), that.getTickets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTickets());
    }
}
