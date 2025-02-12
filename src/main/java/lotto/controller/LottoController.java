package lotto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lotto.domain.Lottos;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private Lottos lottos;

    public void run() {
        int money = inputView.inputMoney();
        int lottoCounts = money / 1000;
        outputView.printCount(lottoCounts);
        lottos = new Lottos(lottoCounts);
        outputView.printLottos(lottos);
        List<Integer> test = inputView.inputWinningLotto();
    }



}
