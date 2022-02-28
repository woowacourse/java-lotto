package lotto.service;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.money.Money;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.WinningTicket;
import lotto.domain.ticket.generator.TicketGenerator;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;

public class LottoService {

    private final TicketGenerator ticketGenerator;

    private Tickets tickets;

    public LottoService(final TicketGenerator ticketGenerator) {
        this.ticketGenerator = ticketGenerator;
    }

    public void generateTickets(final int money) {
        final int ticketCount = (new Money(money)).getQuotient();
        this.tickets = new Tickets(ticketCount, ticketGenerator);
    }

    public AnalysisDto generateAnalysis(final WinningTicketDto winningTicketDto) {
        final WinningTicket winningTicket = winningTicketDto.toWinningTicket();
        final List<Rank> ranks = winningTicket.calculateRanks(tickets);
        return new AnalysisDto(ranks, tickets.getSize());
    }

    public List<TicketDto> getTicketDtos() {
        return tickets.getTickets().stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

}
