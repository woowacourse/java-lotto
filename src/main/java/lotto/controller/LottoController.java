package lotto.controller;

import java.util.HashSet;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.WinningResult;
import lotto.domain.WinningResultCalculator;
import lotto.util.StringParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final String DELIMITER = ",";

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
        final int lottoPrice = StringParser.parseInt(inputView.readPurchasePrice());
        final int lottoCount = calculateLottoCount(lottoPrice);
        final List<Lotto> lottos = lottoGenerator.generate(lottoCount);
        printPurchasedLottos(lottos);
        printWinningResult(lottos, lottoPrice);
    }

    private int calculateLottoCount(final int lottoPrice) {
        final int lottoCount = lottoGenerator.calculateLottoCount(lottoPrice);
        outputView.printLottoCount(lottoCount);
        return lottoCount;
    }

    private void printPurchasedLottos(final List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::getSortedNumbers)
                .forEach(System.out::println);
    }

    private void printWinningResult(final List<Lotto> lottos, final int lottoPrice) {
        final WinningResultCalculator winningResultCalculator = makeWinningResultCalculator();
        final WinningResult winningResult = winningResultCalculator.makeWinningResult(lottos);
        outputView.printWinningResult(winningResult.getWinningResult());
        outputView.printProfitRate(winningResult.calculateProfitRate(lottoPrice));
    }

    private WinningResultCalculator makeWinningResultCalculator() {
        final Lotto winningLotto = makeWinningLotto();
        final LottoNumber bonusNumber = makeBonusNumber();
        return new WinningResultCalculator(winningLotto, bonusNumber);
    }

    private Lotto makeWinningLotto() {
        final String winningLottoNumbers = inputView.readWinningLottoNumbers();
        final String[] splitWinningLottoNumbers = winningLottoNumbers.split(DELIMITER);
        final List<Integer> parsedWinningLottoNumbers = StringParser.parseTokens(splitWinningLottoNumbers);
        return new Lotto(new HashSet<>(parsedWinningLottoNumbers));
    }

    private LottoNumber makeBonusNumber() {
        final String bonusNumber = inputView.readBonusNumber();
        final int parsedBonusNumber = StringParser.parseInt(bonusNumber);
        return LottoNumber.from(parsedBonusNumber);
    }
}
