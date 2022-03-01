package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.analysis.Analysis;
import lotto.domain.money.Money;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.TicketManager;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningTicket;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.TicketManagerDto;
import lotto.dto.WinningTicketDto;
import lotto.view.LottoView;

public class LottoController {

    public final LottoView lottoView;

    public LottoController(final LottoView lottoView) {
        this.lottoView = lottoView;
    }

    public void run() {
        final TicketManager ticketManager = purchaseTickets();
        announceTickets(ticketManager);

        final Analysis analysis = calculateAnalysis(ticketManager);
        announceAnalysis(analysis);
    }

    private TicketManager purchaseTickets() {
        final int totalTicketCount = calculateTotalTicketCount();
        final int manualTicketCount = lottoView.requestManualTicketCount(totalTicketCount);
        final Tickets manualTickets = requestManualTickets(manualTicketCount);
        return TicketManager.generateTickets(totalTicketCount, manualTickets, new RandomTicketGenerator());
    }

    private int calculateTotalTicketCount() {
        final Money money = new Money(lottoView.requestMoney());
        return money.getQuotient();
    }

    private Tickets requestManualTickets(final int manualTicketCount) {
        final List<TicketDto> manualTicketDtos = lottoView.requestManualTicketDtos(manualTicketCount);
        final List<Ticket> manualTickets = manualTicketDtos.stream()
                .map(TicketDto::toTicket)
                .collect(Collectors.toUnmodifiableList());
        return Tickets.generateTickets(manualTickets);
    }

    private void announceTickets(final TicketManager ticketManager) {
        final TicketManagerDto ticketManagerDto = TicketManagerDto.toDto(ticketManager);
        lottoView.announceTickets(ticketManagerDto);
    }

    private Analysis calculateAnalysis(final TicketManager ticketManager) {
        final WinningTicket winningTicket = this.requestWinningTicket();
        final List<Rank> ranks = ticketManager.calculateRanks(winningTicket);
        return new Analysis(ranks, ticketManager.getSize());
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
