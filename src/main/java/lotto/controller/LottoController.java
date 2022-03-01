package lotto.controller;

import static lotto.view.output.OutputMessage.DIVIDING_LINE;
import static lotto.view.output.OutputMessage.EMPTY_STRING;
import static lotto.view.output.OutputMessage.REQUEST_BONUS_NUMBER;
import static lotto.view.output.OutputMessage.REQUEST_MONEY;
import static lotto.view.output.OutputMessage.REQUEST_WINNING_NUMBERS;
import static lotto.view.output.OutputMessage.TITLE_OF_ANALYSIS;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.Analysis;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.WinningTicket;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
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

    public Tickets purchaseTickets() {
        final int money = this.requestMoney();
        return lottoService.generateTickets(money);
    }

    public void showTickets(Tickets tickets) {
        final List<TicketDto> ticketDtos = tickets.getTickets().stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
        this.announceTickets(ticketDtos);
    }

    public lotto.domain.ticket.Analysis calculateAnalysis(Tickets tickets) {
        final WinningTicketDto winningTicketDto = this.requestWinningTicket();
        final WinningTicket winningTicket = winningTicketDto.toWinningTicket();
        return lottoService.generateAnalysis(tickets, winningTicket);
    }

    private void showAnalysis(final lotto.domain.ticket.Analysis analysis) {
        final AnalysisDto analysisDto = AnalysisDto.toDto(analysis);
        this.announceAnalysis(analysisDto);
    }

    public int requestMoney() {
        outputView.printMessage(REQUEST_MONEY);
        return inputView.requestMoney();
    }

    public WinningTicketDto requestWinningTicket() {
        final List<Integer> winningNumbers = requestWinningNumbers();
        final int bonusNumber = requestBonusNumber();
        return new WinningTicketDto(winningNumbers, bonusNumber);
    }

    private List<Integer> requestWinningNumbers() {
        outputView.printMessage(REQUEST_WINNING_NUMBERS);
        return inputView.requestWinningNumbers();
    }

    private int requestBonusNumber() {
        outputView.printMessage(REQUEST_BONUS_NUMBER);
        return inputView.requestBonusNumber();
    }

    public void announceTickets(List<TicketDto> ticketDtos) {
        outputView.printTicketCount(ticketDtos);
        outputView.printTickets(ticketDtos);
    }

    public void announceAnalysis(AnalysisDto analysis) {
        outputView.printMessage(EMPTY_STRING);
        outputView.printMessage(TITLE_OF_ANALYSIS);
        outputView.printMessage(DIVIDING_LINE);
        outputView.printAnalysis(analysis);
    }

}
