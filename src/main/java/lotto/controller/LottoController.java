package lotto.controller;

import java.io.IOException;
import java.util.List;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicketFactory;
import lotto.domain.Money;
import lotto.domain.WinningStatistics;
import lotto.domain.lottonumbers.LottoTicket;
import lotto.domain.lottonumbers.WinningNumbers;
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
        IndividualInput<Money> input = () -> new Money(InputView.inputMoney());
        return commonInputProcess(input);
    }

    private WinningNumbers inputWinningNumbers() {
        IndividualInput<WinningNumbers> input =
                () -> WinningNumbers.of(InputView.inputWinningNumbers(), InputView.inputBonusBall());
        return commonInputProcess(input);
    }

    private <T> T commonInputProcess(IndividualInput<T> individualInputs) {
        try {
            return individualInputs.get();
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return commonInputProcess(individualInputs);
        }
    }

    private List<LottoTicket> purchaseLottoTickets(Money inputMoney) {
        return LottoTicketFactory.createTickets(inputMoney.getAmount());
    }

    private interface IndividualInput<T> {
        T get() throws IOException, IllegalArgumentException;
    }
}
