package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.view.OutputView;

import java.util.List;

public class OutputViewController {
    private final OutputView outputView = new OutputView();

    public void printLottos(Lottos myLottos) {
        outputView.printLottos(myLottos);
    }

    public void printResult(List<Rank> ranks, double returnRate) {
        outputView.printResult(ranks, returnRate);
    }
}
