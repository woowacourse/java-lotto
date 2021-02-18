package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.Money;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.LottoStore;
import lotto.domain.lotto.lottogenerator.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(Scanner scanner) {
        this.inputView = new InputView(scanner);
    }

    public void play() {
        OutputView.inputMoney();
        Money money = inputView.inputMoney();
        LottoStore lottoStore = new LottoStore();
        Lottos purchasedLottos = lottoStore.buyLotto(money);
        OutputView.numPurchasedLotto(purchasedLottos.getNumLotto());
        OutputView.lottosPrint(purchasedLottos);
    }
}


