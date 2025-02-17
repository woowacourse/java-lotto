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
        LottoPrice lottoPrice = getLottoPrice();
        int lottoCount = getLottoCount(lottoPrice);
        outputView.printLottoCount(lottoCount);
        List<Lotto> lottos = lottoGenerator.generateLotto(lottoCount);
        printPurchaseLottos(lottos);

        WinningResultCalculator winningResultCalculator = getWinningResultCalculator();
        WinningResult winningResult = winningResultCalculator.countLottoPrizes(lottos);
        outputView.printWinningResult(winningResult.getWinningResult());
        outputView.printProfitRate(winningResult.calculateProfitRate(lottoPrice));
    }

    private LottoPrice getLottoPrice() {
        int lottoPrice = inputView.readPurchasePrice();
        return new LottoPrice(lottoPrice);
    }

    private int getLottoCount(final LottoPrice lottoPrice) {
        int lottoCount = lottoPrice.calculateLottoCount();
        return lottoCount;
    }

    private void printPurchaseLottos(final List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

    private WinningResultCalculator getWinningResultCalculator() {
        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber();
        return new WinningResultCalculator(winningLotto, bonusNumber);
    }

    private Lotto getWinningLotto() {
        List<Integer> winningLottoNumbers = inputView.readWinningLottoNumbers();
        Lotto winningLotto = new Lotto(winningLottoNumbers);
        return winningLotto;
    }

    private LottoNumber getBonusNumber() {
        int bonusNumberInput = inputView.readBonusNumber();
        LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
        return bonusNumber;
    }
}
