package lotto.game;

import lotto.ticket.Ticket;
import lotto.ticket.Tickets;
import lotto.ticket.strategy.ManualNumbersGenerator;
import lotto.ticket.strategy.NumbersGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoService {
    private final NumbersGenerator autoLottoGenerator;

    public LottoService(NumbersGenerator generator) {
        this.autoLottoGenerator = generator;
    }

    public Tickets buyManualTickets(List<String> manualLottoNumber) {
        List<Ticket> manualTickets = manualLottoNumber.stream()
                .map(ManualNumbersGenerator::new)
                .map(ManualNumbersGenerator::generate)
                .map(Ticket::new)
                .collect(Collectors.toList());
        return new Tickets(manualTickets);
    }

    public Tickets buyAutoTickets(LottoCount count) {
        List<Ticket> tickets = new ArrayList<>();
        while (count.isGreaterThanZero()) {
            count = count.decreaseOne();
            tickets.add(new Ticket(autoLottoGenerator.generate()));
        }
        return new Tickets(tickets);
    }
}
