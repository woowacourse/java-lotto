package lotto.view;

import static lotto.view.output.OutputMessage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.dto.AnalysisDto;
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
        final int manualTicketCount = inputView.requestTicketCount();
        verifyManualTicketCountIsLessThanTotalTicketCount(manualTicketCount, totalTicketCount);
        return manualTicketCount;
    }

    private void verifyManualTicketCountIsLessThanTotalTicketCount(final int manulTicketCount,
                                                                  final int totalTicketCount) {
        if (manulTicketCount > totalTicketCount) {
            throw new IllegalArgumentException();
        }
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

    public void announceTickets(final List<TicketDto> ticketDtos) {
        outputView.printMessage(EMPTY_STRING);
        outputView.printTicketCount(ticketDtos);
        outputView.printTickets(ticketDtos);
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
