package lotto.controller;

import java.io.IOException;
import java.util.List;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicketFactory;
import lotto.domain.TryMoney;
import lotto.domain.WinningStats;
import lotto.domain.lottonumbers.LottoTicket;
import lotto.domain.lottonumbers.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        TryMoney inputTryMoney = inputMoney();

        List<LottoTicket> lottoTickets = purchaseLottoTickets(inputTryMoney);
        OutputView.printPurchasedTickets(lottoTickets);

        WinningNumbers winningNumbers = inputWinningNumbers();

        WinningStats winningStats = calculateStatistics(lottoTickets, winningNumbers);

        OutputView.printStatistics(winningStats, inputTryMoney);
    }

    private WinningStats calculateStatistics(List<LottoTicket> lottoTickets, WinningNumbers winningNumbers) {
        WinningStats winningStats = new WinningStats();

        for (LottoTicket lottoTicket : lottoTickets) {
            LottoRank lottoRank = LottoRank.getRank(
                    winningNumbers.countContaining(lottoTicket),
                    winningNumbers.containBonusBall(lottoTicket)
            );
            winningStats.put(lottoRank);
        }
        return winningStats;
    }

    private TryMoney inputMoney() {
        IndividualInput<TryMoney> input = () -> new TryMoney(InputView.inputMoney());
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

    private List<LottoTicket> purchaseLottoTickets(TryMoney inputTryTryMoney) {
        return LottoTicketFactory.createTickets(inputTryTryMoney);
    }

    private interface IndividualInput<T> {
        T get() throws IOException, IllegalArgumentException;
    }
}
