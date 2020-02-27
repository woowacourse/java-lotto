package lotto.domain;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    private LottosGenerator lottosGenerator;

    public LottoGame(LottosGenerator lottosGenerator) {
        this.lottosGenerator = lottosGenerator;
    }

    public void play() {
        Money purchaseAmount = InputView.inputPurchaseAmount();
        Money manualLottosAmount = InputView.inputManualLottoAmount();

        Lottos lottos = purchaseLottos(purchaseAmount, manualLottosAmount);
        Result result = produceResult(lottos, purchaseAmount);
        OutputView.printResult(result);
    }

    private Lottos generateManualLottos(Money purchaseAmount, Money manualLottosAmount) {
        Lottos manualLottos = InputView.inputManualLottos(manualLottosAmount);
        OutputView.printLottosSize(manualLottosAmount.getValue(),
            purchaseAmount.toLottosSizeExcept(manualLottosAmount));
        OutputView.printLottos(manualLottos);
        return manualLottos;
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

    private Lottos purchaseLottos(Money purchaseAmount, Money manualLottosAmount) {
        Lottos manualLottos = generateManualLottos(purchaseAmount, manualLottosAmount);
        Lottos autoLottos = generateAutoLottos(purchaseAmount.toLottosSizeExcept(manualLottosAmount));
        return manualLottos.add(autoLottos);
    }

    private Lottos generateAutoLottos(int lottosSize) {
        Lottos lottos = lottosGenerator.generate(lottosSize);
        OutputView.printLottos(lottos);
        return lottos;
    }

}
