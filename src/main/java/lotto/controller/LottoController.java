package lotto.controller;

import static lotto.view.output.OutputMessage.*;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.Analysis;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.WinningTicket;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoController {

    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final LottoService lottoService, final InputView inputView, final OutputView outputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Tickets tickets = purchaseTickets();
        showTickets(tickets);

        final Analysis analysis = calculateAnalysis(tickets);
        showAnalysis(analysis);
    }

    private Tickets purchaseTickets() {
        final int money = this.requestMoney();
        return lottoService.generateTickets(money);
    }

    private int requestMoney() {
        outputView.printMessage(REQUEST_MONEY);
        return inputView.requestMoney();
    }

    private void showTickets(Tickets tickets) {
        final List<TicketDto> ticketDtos = tickets.getTickets().stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
        this.announceTickets(ticketDtos);
    }

    private void announceTickets(List<TicketDto> ticketDtos) {
        outputView.printTicketCount(ticketDtos);
        outputView.printTickets(ticketDtos);
    }

    private Analysis calculateAnalysis(Tickets tickets) {
        final WinningTicket winningTicket = this.requestWinningTicket();
        return lottoService.generateAnalysis(tickets, winningTicket);
    }

    private WinningTicket requestWinningTicket() {
        final List<Integer> winningNumbers = requestWinningNumbers();
        final int bonusNumber = requestBonusNumber();
        return new WinningTicket(winningNumbers, bonusNumber);
    }

    private List<Integer> requestWinningNumbers() {
        outputView.printMessage(REQUEST_WINNING_NUMBERS);
        return inputView.requestTicketNumbers();
    }

    private int requestBonusNumber() {
        outputView.printMessage(REQUEST_BONUS_NUMBER);
        return inputView.requestBonusNumber();
    }

    private void showAnalysis(final lotto.domain.ticket.Analysis analysis) {
        final AnalysisDto analysisDto = AnalysisDto.toDto(analysis);
        this.announceAnalysis(analysisDto);
    }

    private void announceAnalysis(AnalysisDto analysis) {
        outputView.printMessage(EMPTY_STRING);
        outputView.printMessage(TITLE_OF_ANALYSIS);
        outputView.printMessage(DIVIDING_LINE);
        outputView.printAnalysis(analysis);
    }

}
