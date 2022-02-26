package lotto.controller;

import java.util.List;

import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.TicketsDto;
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

    public void purchaseTickets() {
        generateTickets();
        announceTickets();
    }

    private void generateTickets() {
        final int money = lottoView.requestMoney();
        lottoService.saveCredit(money);
        lottoService.generateTickets();
    }

    private void announceTickets() {
        final TicketsDto ticketDtos = lottoService.getTicketDtos();
        lottoView.announceTickets(ticketDtos);
    }

    public void checkOutAnalysis() {
        final WinningTicketDto winningTicketDto = lottoView.requestWinningTicket();
        final AnalysisDto analysisDto = lottoService.generateAnalysis(winningTicketDto);
        lottoView.announceAnalysis(analysisDto);
    }

}
