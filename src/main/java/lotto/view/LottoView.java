package lotto.view;

import static lotto.view.output.OutputMessage.DIVIDING_LINE;
import static lotto.view.output.OutputMessage.EMPTY_STRING;
import static lotto.view.output.OutputMessage.REQUEST_BONUS_NUMBER;
import static lotto.view.output.OutputMessage.REQUEST_MANUAL_TICKETS;
import static lotto.view.output.OutputMessage.REQUEST_MANUAL_TICKET_COUNT;
import static lotto.view.output.OutputMessage.REQUEST_MONEY;
import static lotto.view.output.OutputMessage.REQUEST_WINNING_NUMBERS;
import static lotto.view.output.OutputMessage.TITLE_OF_ANALYSIS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.dto.AnalysisDto;
import lotto.dto.TicketBundlesDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoView {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int requestMoney() {
        outputView.printMessage(REQUEST_MONEY);
        return inputView.requestMoney();
    }

    public int requestManualTicketCount(final int totalTicketCount) {
        outputView.printMessage(EMPTY_STRING);
        outputView.printMessage(REQUEST_MANUAL_TICKET_COUNT);
        return requestTicketCount(totalTicketCount);
    }

    private int requestTicketCount(final int totalTicketCount) {
        final int manualTicketCount = inputView.requestTicketCount();
        InputTicketCountValidator.validateTicketCount(totalTicketCount, manualTicketCount);
        return manualTicketCount;
    }

    public List<TicketDto> requestManualTicketDtos(final int ticketCount) {
        if (ticketCount == 0) {
            return Collections.emptyList();
        }
        outputView.printMessage(EMPTY_STRING);
        outputView.printMessage(REQUEST_MANUAL_TICKETS);
        return requestTicketDtos(ticketCount);
    }

    private List<TicketDto> requestTicketDtos(final int ticketCount) {
        final List<TicketDto> ticketDtos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            final List<Integer> ticketNumbers = inputView.requestTicketNumbers();
            ticketDtos.add(new TicketDto(ticketNumbers));
        }
        return ticketDtos;
    }

    public void announceTickets(final TicketBundlesDto ticketBundlesDto) {
        outputView.printMessage(EMPTY_STRING);
        outputView.printTicketCount(ticketBundlesDto);
        outputView.printAllTickets(ticketBundlesDto);
    }

    public WinningTicketDto requestWinningTicketDto() {
        outputView.printMessage(EMPTY_STRING);
        final List<Integer> winningNumbers = requestWinningNumbers();
        final int bonusNumber = requestBonusNumber();
        return new WinningTicketDto(winningNumbers, bonusNumber);
    }

    private List<Integer> requestWinningNumbers() {
        outputView.printMessage(REQUEST_WINNING_NUMBERS);
        return inputView.requestTicketNumbers();
    }

    private int requestBonusNumber() {
        outputView.printMessage(REQUEST_BONUS_NUMBER);
        return inputView.requestBonusNumber();
    }

    public void announceAnalysis(final AnalysisDto analysisDto) {
        outputView.printMessage(EMPTY_STRING);
        outputView.printMessage(TITLE_OF_ANALYSIS);
        outputView.printMessage(DIVIDING_LINE);
        outputView.printAnalysis(analysisDto);
    }

}
