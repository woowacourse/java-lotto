package controller;

import domain.lotto.LottoTickets;
import domain.rank.Rank;
import domain.rank.Ranks;
import domain.lotto.Result;
import domain.Transaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        final Transaction transaction = new Transaction(InputView.payment());
        int manualCount = InputView.manualCount();
        calculateResult(purchaseLotto(transaction, manualCount), transaction);
    }

    private LottoTickets purchaseLotto(final Transaction transaction, final int manualCount) {
        transaction.buyManualLotto(manualCount);
        final LottoTickets lottoTickets = new LottoTickets();

        lottoTickets.generateManual(insertManualNumbers(manualCount));
        lottoTickets.generateAuto(transaction);

        OutputView.transactionInfo(transaction);
        OutputView.ticketInfo(lottoTickets);

        return lottoTickets;
    }

    private List<List<Integer>> insertManualNumbers(final int manualCount) {
        if (manualCount == 0) {
            return Collections.emptyList();
        }

        InputView.printManualNumberMessage();
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualCount; ++i) {
            manualNumbers.add(InputView.manualNumbers());
        }
        return manualNumbers;
    }

    private void calculateResult(final LottoTickets lottoTickets, final Transaction transaction) {
        final Result result = new Result(InputView.winningNumbers(), InputView.bonusNumber());
        final Ranks ranks = result.calculate(lottoTickets);

        OutputView.resultTitle();
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NOTHING)
                .forEach(rank -> OutputView.rankInfo(ranks.count(rank), rank));

        OutputView.totalProfitRate(transaction.calculateTotalProfitRate(ranks.totalProfit()));
    }
}
