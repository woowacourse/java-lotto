package lotto.controller;

import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.model.LottoResult;
import lotto.model.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void play() {
        Money purchaseAmount = Money.priceOf(InputView.inputPurchaseAmount());
        Lottos purchasedLottos = Lottos.buyLotto(purchaseAmount);
        OutputView.printLottos(purchasedLottos);

        Lotto winningLottoNumber = Lotto.of(InputView.inputWinningLottoNumbers());
        int bonus = InputView.inputWinningBonus();
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonus);
        LottoResult lottoResult = purchasedLottos.match(winningLotto);
        OutputView.printLottoResult(lottoResult);
    }
}


