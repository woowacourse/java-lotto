package controller;

import domain.Wallet;
import domain.lotto.LottoTickets;
import domain.lotto.Result;
import domain.rank.Ranks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        final Wallet wallet = new Wallet(InputView.payment());
        final int manualCount = InputView.manualCount();
        calculateResult(purchaseLotto(wallet, manualCount), wallet);
    }

    private LottoTickets purchaseLotto(final Wallet wallet, final int manualCount) {
        wallet.buyManualLotto(manualCount);
        final LottoTickets lottoTickets = new LottoTickets();

        lottoTickets.generateManual(insertManualNumbers(manualCount));
        lottoTickets.generateAuto(wallet);

        OutputView.transactionInfo(wallet);
        OutputView.ticketInfo(lottoTickets);

        return lottoTickets;
    }

    private List<List<Integer>> insertManualNumbers(final int manualCount) {
        if (manualCount == 0) {
            return Collections.emptyList();
        }
        return InputView.manualNumbers(manualCount);
    }

    private void calculateResult(final LottoTickets lottoTickets, final Wallet wallet) {
        final Result result = new Result(InputView.winningNumbers(), InputView.bonusNumber());
        final Ranks ranks = result.calculate(lottoTickets);

        OutputView.result(ranks);
        OutputView.totalProfitRate(wallet.calculateTotalProfitRate(ranks.totalProfit()));
    }
}
