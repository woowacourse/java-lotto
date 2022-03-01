package lotto.view;

import static lotto.view.output.OutputMessage.*;

import java.util.List;

import lotto.domain.analysis.Analysis;
import lotto.domain.ticket.Tickets;
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

    public void announceTickets(final Tickets tickets) {
        outputView.printTicketCount(tickets);
        outputView.printTickets(tickets);
    }

    public List<Integer> requestWinningNumbers() {
        outputView.printMessage(REQUEST_WINNING_NUMBERS);
        return inputView.requestTicketNumbers();
    }

    public int requestBonusNumber() {
        outputView.printMessage(REQUEST_BONUS_NUMBER);
        return inputView.requestBonusNumber();
    }

    public void announceAnalysis(final Analysis analysis) {
        outputView.printMessage(EMPTY_STRING);
        outputView.printMessage(TITLE_OF_ANALYSIS);
        outputView.printMessage(DIVIDING_LINE);
        outputView.printAnalysis(analysis);
    }

}
