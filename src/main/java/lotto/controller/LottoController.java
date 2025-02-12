package lotto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private Lottos lottos;
    private WinningLotto winningLotto;

    public void run() {
        int money = inputView.inputMoney();
        int lottoCounts = money / 1000;
        outputView.printCount(lottoCounts);
        lottos = new Lottos(lottoCounts);
        outputView.printLottos(lottos);

        Lotto winningLottoNumber = new Lotto(inputView.inputWinningLotto());
        int bonusNumber = inputView.inputBonusNumber();
        winningLotto = new WinningLotto(winningLottoNumber, bonusNumber);


    }
}
