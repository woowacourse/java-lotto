package lotto;

import lotto.domain.*;
import lotto.domain.factory.LottoTicketsFactory;
import lotto.domain.factory.WinningLottoFactory;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

import java.util.List;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(ConsoleInputView.inputMoney());

        ConsoleOutputView.printAmount(lottoMoney);

        List<LottoTicket> lottoTickets = LottoTicketsFactory.create(lottoMoney);
        ConsoleOutputView.printTickets(lottoTickets);

        WinningLotto winningLotto = WinningLottoFactory.create(ConsoleInputView.inputRewardTicket(), ConsoleInputView.inputBonusBall());

        LottoResults lottoResults = new LottoResults(lottoTickets, winningLotto, lottoMoney);
        ConsoleOutputView.printResults(lottoResults);
    }
}
