package lotto.controller;

import java.io.IOException;
import java.util.List;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicketFactory;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningStats;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        PurchaseAmount purchaseAmount = inputMoney();

        List<LottoTicket> lottoTickets = purchaseLottoTickets(purchaseAmount);
        OutputView.printPurchasedTickets(lottoTickets);

        WinningNumbers winningNumbers = inputWinningNumbers();

        WinningStats winningStats = calculateStatistics(lottoTickets, winningNumbers);

        OutputView.printStatistics(winningStats, purchaseAmount);
    }

    private List<LottoTicket> purchaseLottoTickets(PurchaseAmount purchaseAmount) {
        return LottoTicketFactory.createTickets(purchaseAmount);
    }

    private WinningStats calculateStatistics(List<LottoTicket> lottoTickets, WinningNumbers winningNumbers) {
        WinningStats winningStats = new WinningStats();

        for (LottoTicket lottoTicket : lottoTickets) {
            LottoRank lottoRank = LottoRank.getRank(
                    winningNumbers.getMatchCount(lottoTicket),
                    winningNumbers.doesMatchBonusBall(lottoTicket)
            );
            winningStats.put(lottoRank);
        }
        return winningStats;
    }

    private PurchaseAmount inputMoney() {
        IndividualInput<PurchaseAmount> input = () -> new PurchaseAmount(InputView.inputMoney());
        return commonInputProcess(input);
    }

    private WinningNumbers inputWinningNumbers() {
        IndividualInput<WinningNumbers> input =
                () -> new WinningNumbers(InputView.inputWinningNumbers(), InputView.inputBonusBall());
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

    private interface IndividualInput<T> {
        T get() throws IOException, IllegalArgumentException;
    }
}
