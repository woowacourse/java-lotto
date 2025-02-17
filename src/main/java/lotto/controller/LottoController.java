package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPrice;
import lotto.domain.WinningResult;
import lotto.domain.WinningResultCalculator;
import lotto.view.StringToIntParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoController(final InputView inputView, final OutputView outputView,
                           final LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        final LottoPrice lottoPrice = getLottoPrice();
        final int lottoCount = getLottoCount(lottoPrice);
        outputView.printLottoCount(lottoCount);
        final List<Lotto> lottos = lottoGenerator.generateLotto(lottoCount);
        printPurchaseLottos(lottos);

        final WinningResultCalculator winningResultCalculator = getWinningResultCalculator();
        final WinningResult winningResult = winningResultCalculator.countLottoPrizes(lottos);
        outputView.printWinningResult(winningResult.getWinningResult());
        outputView.printProfitRate(winningResult.calculateProfitRate(lottoPrice));
    }

    private LottoPrice getLottoPrice() {
        final int lottoPrice = inputView.readPurchasePrice();
        return new LottoPrice(lottoPrice);
    }

    private int getLottoCount(final LottoPrice lottoPrice) {
        final int lottoCount = lottoPrice.calculateLottoCount();
        return lottoCount;
    }

    private void printPurchaseLottos(final List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

    private WinningResultCalculator getWinningResultCalculator() {
        final Lotto winningLotto = getWinningLotto();
        final LottoNumber bonusNumber = getBonusNumber();
        return new WinningResultCalculator(winningLotto, bonusNumber);
    }

    private Lotto getWinningLotto() {
        final List<Integer> winningLottoNumbers = inputView.readWinningLottoNumbers();
        final Lotto winningLotto = new Lotto(winningLottoNumbers);
        return winningLotto;
    }

    private LottoNumber getBonusNumber() {
        final int bonusNumberInput = inputView.readBonusNumber();
        final LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
        return bonusNumber;
    }
}
