package lotto.controller;

import lotto.domain.Lottos;
import lotto.view.OutputView;

public class OutputViewController {
    private final OutputView outputView = new OutputView();

    public void printLottos(Lottos myLottos) {
        outputView.printLottos(myLottos);
    }
}
