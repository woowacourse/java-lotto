package lotto.controller;

import static lotto.model.Rank.FIFTH;
import static lotto.model.Rank.FIRST;
import static lotto.model.Rank.FOURTH;
import static lotto.model.Rank.SECOND;
import static lotto.model.Rank.THIRD;

import lotto.model.Lottoes;
import lotto.model.Statistic;
import lotto.model.WinnerLotto;
import lotto.view.OutputView;

public class SummarizeController {

    private final WinnerLotto winnerLotto;
    private final Lottoes issuedLottoes;

    public SummarizeController(WinnerLotto winnerLotto, Lottoes issuedLottoes) {
        this.winnerLotto = winnerLotto;
        this.issuedLottoes = issuedLottoes;
    }

    public void run() {
        Statistic statistic = winnerLotto.summarize(issuedLottoes);
        OutputView.printStatistic(statistic.getCountByRank(FIRST), statistic.getCountByRank(SECOND),
            statistic.getCountByRank(THIRD), statistic.getCountByRank(FOURTH),
            statistic.getCountByRank(FIFTH), statistic.getProfitRate().getDoubleValue());
    }

}
