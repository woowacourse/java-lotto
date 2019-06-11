package lotto;

import lotto.domain.LottoMoney;
import lotto.domain.LottoResults;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.domain.factory.LottoResultsFactory;
import lotto.domain.factory.LottoTicketsFactory;
import lotto.domain.factory.WinningLottoFactory;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

import java.util.List;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(ConsoleInputView.inputMoney());
        ConsoleOutputView.printAmount(lottoMoney);

        int manualAmount = ConsoleInputView.inputNumberOfManual();
        List<String> manualLottoNumbers = ConsoleInputView.intputManualLottoNumbers(manualAmount);
        LottoTickets lottoTickets = LottoTicketsFactory.create(manualAmount, manualLottoNumbers, lottoMoney);
        ConsoleOutputView.printAmounts(manualAmount, lottoMoney);
        ConsoleOutputView.printTickets(lottoTickets);

        WinningLotto winningLotto = WinningLottoFactory.create(ConsoleInputView.inputRewardTicket(), ConsoleInputView.inputBonusBall());

        LottoResults lottoResults = LottoResultsFactory.create(lottoTickets, winningLotto, lottoMoney);
        ConsoleOutputView.printResults(lottoResults);
    }
}
