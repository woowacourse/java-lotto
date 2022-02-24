package lotto.view;

import java.util.List;

import lotto.domain.ticket.Tickets;
import lotto.dto.AnalysisDto;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class View {

    private final InputView inputView;
    private final OutputView outputView;

    public View(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int requestCreditMoney() {
        outputView.printMessageOfRequestCreditMoney();
        return inputView.requestCreditMoney();
    }

    public List<Integer> requestWinningNumbers() {
        outputView.printMessageOfRequestWinningNumbers();
        return inputView.requestWinningNumbers();
    }

    public int requestBonusNumber() {
        outputView.printMessageOfRequestBonusNumber();
        return inputView.requestBonusNumber();
    }

    public void announceTickets(Tickets tickets) {
        outputView.printTicketCount(tickets);
        outputView.printTickets(tickets);
    }

    public void announceAnalysis(AnalysisDto analysis) {
        outputView.printEmptyLine();
        outputView.printTitleOfAnalysis();
        outputView.printDividingLine();
        outputView.printAnalysis(analysis);
    }

}
