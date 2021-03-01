package controller;

import domain.Wallet;
import domain.lotto.LottoTickets;
import domain.lotto.Result;
import domain.rank.Ranks;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        final int payment = InputView.payment();
        final int manualCount = InputView.manualCount();
        final Wallet wallet = new Wallet(payment, manualCount);
        final LottoTickets lottoTickets = wallet.purchaseLotto(InputView.manualNumbers(manualCount));
        OutputView.ticketInfo(lottoTickets);

        final Result result = new Result(InputView.winningNumbers(), InputView.bonusNumber());
        final Ranks ranks = result.calculate(lottoTickets);
        OutputView.result(ranks);
        OutputView.totalProfitRate(wallet.calculateTotalProfitRate(ranks.totalProfit()));
    }
}
