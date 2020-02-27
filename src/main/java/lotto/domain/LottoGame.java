package lotto.domain;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    private LottosGenerator lottosGenerator;

    public LottoGame(LottosGenerator lottosGenerator) {
        this.lottosGenerator = lottosGenerator;
    }

    public void play() {
        Money purchaseAmount = inputPurchaseAmount();
        Lottos lottos = purchaseLottos(purchaseAmount);
        Result result = produceResult(lottos, purchaseAmount);
        OutputView.printResult(result);

    }

    private Result produceResult(Lottos lottos, Money purchaseMoney) {
        WinningLotto winningLotto = InputView.inputWinningLotto();
        WinningRanks winningRanks = compareWithWinningNumbers(lottos, winningLotto);
        return new Result(winningRanks, purchaseMoney);
    }

    private WinningRanks compareWithWinningNumbers(Lottos lottos, WinningLotto winningLotto) {
        Ranks ranks = winningLotto.match(lottos);
        return WinningRanksFactory.create(ranks);
    }

    private Lottos purchaseLottos(Money purchaseAmount) {
        return generateLottos(purchaseAmount.toLottosSize());
    }

    private Lottos generateLottos(int lottosSize) {
        Lottos lottos = lottosGenerator.generate(lottosSize);
        OutputView.printLottos(lottos);
        return lottos;
    }

    private Money inputPurchaseAmount() {
        Money purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printLottosSize(purchaseAmount.toLottosSize());
        return purchaseAmount;
    }

}
