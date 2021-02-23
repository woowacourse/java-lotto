package domain.tickets;

import domain.LottoMoney;
import domain.Prize;
import domain.WinningNumbers;
import domain.WinningStatics;
import domain.ticket.LottoTicket;
import domain.ticket.Ticket;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoTickets {
    private final List<Ticket> lottoTickets;

    public AutoLottoTickets(final LottoMoney lottoMoney) {
        final int ticketQuantity = lottoMoney.toTicketQuantity();

        this.lottoTickets = IntStream.range(0, ticketQuantity)
                .mapToObj(i -> new LottoTicket())
                .collect(Collectors.toList());
    }

    public boolean isSameQuantity(final int ticketQuantity) {
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
