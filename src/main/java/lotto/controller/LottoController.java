package lotto.controller;

import java.util.List;

import lotto.domain.*;
import lotto.util.StringParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public static final String DELIMITER = ",";
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
        List<Lotto> lottos = lottoGenerator.generateLotto(lottoCount);
        printPurchaseLottos(lottos);

        WinningResult winningResult = getWinningResult();
    }

    private WinningResult getWinningResult() {
        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber();
        return new WinningResult(winningLotto, bonusNumber);
    }

    private Lotto getWinningLotto() {
        String winningLottoNumbers = inputView.readWinningLottoNumbers();
        String[] splittedWinningLottoNumbers = winningLottoNumbers.split(DELIMITER);
        List<Integer> parsedWinningLottoNumbers = StringParser.parseTokens(splittedWinningLottoNumbers);
        Lotto winningLotto = new Lotto(parsedWinningLottoNumbers);
        return winningLotto;
    }

    private LottoNumber getBonusNumber() {
        String bonusNumberInput = inputView.readBonusNumber();
        int parsedBonusNumber = StringParser.parseInt(bonusNumberInput);
        LottoNumber bonusNumber = new LottoNumber(parsedBonusNumber);
        return bonusNumber;
    }

    private LottoPrice getLottoPrice() {
        int parsedAmount = StringParser.parseInt(inputView.readPurchasePrice());
        return new LottoPrice(parsedAmount);
    }

    private int getLottoCount(final LottoPrice lottoPrice) {
        int lottoCount = lottoPrice.calculateLottoCount();
        outputView.printLottoCount(lottoCount);
        return lottoCount;
    }

    private void printPurchaseLottos(final List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }
}
