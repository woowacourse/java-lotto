package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;
import lotto.view.OutputView;

public class LottoController {

    public Lottos initLottos(Money money) {
        final Lottos lottos = new Lottos(money);
        OutputView.printInitResult(lottos);
        return lottos;
    }

    public void play(Lottos lottos, WinningNumbers winningNumbers, Money money) {
        final Result result = lottos.getResult(winningNumbers);
        OutputView.printPlayResult(result, money);
    }
}
