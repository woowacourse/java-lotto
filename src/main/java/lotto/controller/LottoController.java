package lotto.controller;

import lotto.domain.LottoTickets;
import lotto.domain.MoneyManager;
import lotto.domain.Ranks;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void buyAndMatch() {
        MoneyManager moneyManager = new MoneyManager(InputView.requestMoney());
        LottoTickets lottoTickets = new LottoTickets(moneyManager.getLottoCount());

        OutputView.displayLottoTickets(lottoTickets);

        WinningNumbers winningNumbers = new WinningNumbers(InputView.requestWinningNumbers(),
                InputView.requestBonusNumber());

        Ranks ranks = new Ranks(lottoTickets.getRanksWithWinningNumbers(winningNumbers));
        OutputView.displayResult(ranks.getStatistics(), moneyManager.calculateYield(ranks.getLottoTotalReward()));
    }
}
