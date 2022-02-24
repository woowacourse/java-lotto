package lotto.controller;

import java.io.IOException;
import java.util.List;
import lotto.model.LottoRank;
import lotto.model.LottoTicket;
import lotto.model.LottoTicketFactory;
import lotto.model.Money;
import lotto.model.WinningNumbers;
import lotto.model.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money inputMoney = inputMoney();

        List<LottoTicket> lottoTickets = purchaseLottoTickets(inputMoney);
        OutputView.printPurchasedTickets(lottoTickets);

        WinningNumbers winningNumbers = inputWinningNumbers();

        WinningStatistics winningStatistics = calculateStatistics(lottoTickets, winningNumbers);

        OutputView.printStatistics(winningStatistics, inputMoney);
    }

    private WinningStatistics calculateStatistics(List<LottoTicket> lottoTickets, WinningNumbers winningNumbers) {
        WinningStatistics winningStatistics = new WinningStatistics();

        for (LottoTicket lottoTicket : lottoTickets) {
            LottoRank lottoRank = LottoRank.getRank(
                    winningNumbers.countContaining(lottoTicket),
                    winningNumbers.containBonusBall(lottoTicket)
            );
            winningStatistics.put(lottoRank);
        }
        return winningStatistics;
    }

    private Money inputMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private WinningNumbers inputWinningNumbers() {
        try {
            return WinningNumbers.of(InputView.inputWinningNumbers(), InputView.inputBonusBall());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private List<LottoTicket> purchaseLottoTickets(Money inputMoney) {
        LottoTicketFactory ticketFactory = LottoTicketFactory.getInstance();
        return ticketFactory.createTickets(inputMoney.getAmount());
    }
}
