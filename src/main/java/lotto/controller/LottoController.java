package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPrice;
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
        final LottoPrice lottoPrice = getLottoPrice();
        final int lottoCount = getLottoCount(lottoPrice);
        final List<Lotto> lottos = lottoGenerator.generateLotto(lottoCount);
        printPurchasedLottos(lottos);
        printWinningResult(lottos, lottoPrice);
    }

    private void printWinningResult(final List<Lotto> lottos, final LottoPrice lottoPrice) {
        final WinningResultCalculator winningResultCalculator = getWinningResult();
        final WinningResult winningResult = winningResultCalculator.makeWinningResult(lottos);
        outputView.printWinningResult(winningResult.getWinningResult());
        outputView.printProfitRate(winningResult.calculateProfitRate(lottoPrice));
    }

    private WinningResultCalculator getWinningResult() {
        final Lotto winningLotto = getWinningLotto();
        final LottoNumber bonusNumber = getBonusNumber();
        return new WinningResultCalculator(winningLotto, bonusNumber);
    }

    private Lotto getWinningLotto() {
        final String winningLottoNumbers = inputView.readWinningLottoNumbers();
        final String[] splitWinningLottoNumbers = winningLottoNumbers.split(DELIMITER);
        final List<Integer> parsedWinningLottoNumbers = StringParser.parseTokens(splitWinningLottoNumbers);
        return new Lotto(parsedWinningLottoNumbers);
    }

    private LottoNumber getBonusNumber() {
        final String bonusNumber = inputView.readBonusNumber();
        final int parsedBonusNumber = StringParser.parseInt(bonusNumber);
        return new LottoNumber(parsedBonusNumber);
    }

    private LottoPrice getLottoPrice() {
        final int parsedAmount = StringParser.parseInt(inputView.readPurchasePrice());
        return new LottoPrice(parsedAmount);
    }

    private int getLottoCount(final LottoPrice lottoPrice) {
        final int lottoCount = lottoPrice.calculateLottoCount();
        outputView.printLottoCount(lottoCount);
        return lottoCount;
    }

    private void printPurchasedLottos(final List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }
}
