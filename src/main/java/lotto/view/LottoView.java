package lotto.view;

import java.util.List;

import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoView {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int requestCreditMoney() {
        outputView.printMessageOfRequestCreditMoney();
        return inputView.requestCreditMoney();
    }

    public WinningTicketDto requestWinningTicket() {
        final List<Integer> winningNumbers = requestWinningNumbers();
        final int bonusNumber = requestBonusNumber();
        return new WinningTicketDto(winningNumbers, bonusNumber);
    }

    private List<Integer> requestWinningNumbers() {
        outputView.printMessageOfRequestWinningNumbers();
        return inputView.requestWinningNumbers();
    }

    private int requestBonusNumber() {
        outputView.printMessageOfRequestBonusNumber();
        return inputView.requestBonusNumber();
    }

    public void announceTickets(List<TicketDto> ticketDtos) {
        outputView.printTicketCount(ticketDtos);
        outputView.printTickets(ticketDtos);
    }

    public void announceAnalysis(AnalysisDto analysis) {
        outputView.printEmptyLine();
        outputView.printTitleOfAnalysis();
        outputView.printDividingLine();
        outputView.printAnalysis(analysis);
    }

}
