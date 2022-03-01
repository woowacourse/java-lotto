package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.Analysis;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.WinningTicket;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
import lotto.service.LottoService;
import lotto.view.LottoView;

public class LottoController {

    private final LottoService lottoService;
    private final LottoView lottoView;

    public LottoController(final LottoService lottoService, final LottoView lottoView) {
        this.lottoService = lottoService;
        this.lottoView = lottoView;
    }

    public void run() {
        final Tickets tickets = purchaseTickets();
        showTickets(tickets);

        final Analysis analysis = calculateAnalysis(tickets);
        showAnalysis(analysis);
    }

    public Tickets purchaseTickets() {
        final int money = lottoView.requestMoney();
        return lottoService.generateTickets(money);
    }

    public void showTickets(Tickets tickets) {
        final List<TicketDto> ticketDtos = tickets.getTickets().stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
        lottoView.announceTickets(ticketDtos);
    }

    public lotto.domain.ticket.Analysis calculateAnalysis(Tickets tickets) {
        final WinningTicketDto winningTicketDto = lottoView.requestWinningTicket();
        final WinningTicket winningTicket = winningTicketDto.toWinningTicket();
        return lottoService.generateAnalysis(tickets, winningTicket);
    }

    private void showAnalysis(final lotto.domain.ticket.Analysis analysis) {
        final AnalysisDto analysisDto = AnalysisDto.toDto(analysis);
        lottoView.announceAnalysis(analysisDto);
    }

}
