package domain.tickets;

import domain.Prize;
import domain.TicketQuantity;
import domain.WinningNumbers;
import domain.WinningStatics;
import domain.ticket.LottoTicket;
import domain.ticket.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private final List<Ticket> lottoTickets = new ArrayList<>();

    public LottoTickets(final TicketQuantity ticketQuantity) {
        final int autoAmount = ticketQuantity.getAutoAmount();

        lottoTickets.addAll(createAutoLottoTickets(autoAmount));
    }

    public LottoTickets(final TicketQuantity ticketQuantity, final List<List<Integer>> manualNumbers) {
        final int manualAmount = ticketQuantity.getManualAmount();
        final int autoAmount = ticketQuantity.getAutoAmount();

        lottoTickets.addAll(createManualLottoTickets(manualAmount, manualNumbers));
        lottoTickets.addAll(createAutoLottoTickets(autoAmount));
    }

    private List<Ticket> createManualLottoTickets(final int manualAmount, final List<List<Integer>> manualNumbers) {
        return IntStream.range(0, manualAmount)
                .mapToObj(i -> new LottoTicket(manualNumbers.get(i)))
                .collect(Collectors.toList());
    }

    private List<Ticket> createAutoLottoTickets(final int autoAmount) {
        return IntStream.range(0, autoAmount)
                .mapToObj(i -> new LottoTicket())
                .collect(Collectors.toList());
    }

    public boolean isSameTotalQuantity(final int ticketQuantity) {
        return lottoTickets.size() == ticketQuantity;
    }

    public WinningStatics calculateWinningStatics(final WinningNumbers winningNumbers) {
        final List<Prize> prizes = calculatePrizes(winningNumbers);
        return new WinningStatics(prizes);
    }

    private List<Prize> calculatePrizes(final WinningNumbers winningNumbers) {
        return this.lottoTickets
                .stream()
                .map(ticket -> Prize.of(winningNumbers, LottoTicket.class.cast(ticket)))
                .collect(Collectors.toList());
    }

    public List<Ticket> toList() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
