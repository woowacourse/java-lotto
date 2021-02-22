package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottogenerator.RandomLottoGenerator;
import lotto.exception.LessThanLottoPriceException;
import lotto.model.LottoResult;
import lotto.model.Money;
import lotto.utils.InputValidationUtils;
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


