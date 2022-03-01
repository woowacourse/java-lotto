package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.analysis.Analysis;
import lotto.domain.money.Money;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningTicket;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
import lotto.view.LottoView;

public class LottoController {

    public final LottoView lottoView;

    public LottoController(final LottoView lottoView) {
        this.lottoView = lottoView;
    }

    public void run() {
        final Tickets tickets = purchaseTickets();
        announceTickets(tickets);

        final Analysis analysis = calculateAnalysis(tickets);
        announceAnalysis(analysis);
    }

    private Tickets purchaseTickets() {
        final int totalTicketCount = calculateTotalTicketCount();
        final int manualTicketCount = lottoView.requestManualTicketCount(totalTicketCount);
        final List<Ticket> manualTickets = requestManualTickets(manualTicketCount);
        return Tickets.generateTickets(totalTicketCount, manualTickets, new RandomTicketGenerator());
    }

    private int calculateTotalTicketCount() {
        final Money money = new Money(lottoView.requestMoney());
        return money.getQuotient();
    }

    private List<Ticket> requestManualTickets(final int manualTicketCount) {
        final List<TicketDto> manualTicketDtos = lottoView.requestManualTicketDtos(manualTicketCount);
        return manualTicketDtos.stream()
                .map(TicketDto::toTicket)
                .collect(Collectors.toUnmodifiableList());
    }

    private void announceTickets(final Tickets tickets) {
        final List<TicketDto> ticketDtos = tickets.getTickets().stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
        lottoView.announceTickets(ticketDtos);
    }

    private Analysis calculateAnalysis(final Tickets tickets) {
        final WinningTicket winningTicket = this.requestWinningTicket();
        final List<Rank> ranks = tickets.calculateRanks(winningTicket);
        return new Analysis(ranks, tickets.getSize());
    }

    private WinningTicket requestWinningTicket() {
        final WinningTicketDto winningTicketDto = lottoView.requestWinningTicketDto();
        return winningTicketDto.toWinningTicket();
    }

    private void announceAnalysis(final Analysis analysis) {
        final AnalysisDto analysisDto = AnalysisDto.toDto(analysis);
        lottoView.announceAnalysis(analysisDto);
    }

}
