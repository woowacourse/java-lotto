package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Prize;
import lotto.domain.WinningLotto;
import java.util.List;
import lotto.util.NumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final int purchaseAmount = readPurchaseAmount();
        Lottos lottos = createLottos(purchaseAmount);

        final List<Integer> winningNumbers = readWinningNumbers();
        final int bonusNumber = readBonusNumber();

        final WinningLotto winningLotto = WinningLotto.of(Lotto.of(winningNumbers), bonusNumber);
        List<Prize> prizes = winningLotto.calculatePrizes(lottos);

        outputView.printLottoResult(prizes, Prize.calculateEarningRate(prizes, lottos.getQuantity() * 1000));
        inputView.close();
    }

    private int readPurchaseAmount() {
        String rawPurchaseAmount = inputView.readPurchaseAmount();
        return NumberParser.parse(rawPurchaseAmount);
    }

    private List<Integer> readWinningNumbers() {
        String rawWinningNumber = inputView.readWinningNumber();
        return NumberParser.parseFromCSV(rawWinningNumber);
    }

    private int readBonusNumber() {
        final String rawBonusNumber = inputView.readBonusNumber();
        return NumberParser.parse(rawBonusNumber);
    }

    private Lottos createLottos(final int purchaseAmount) {
        Lottos lottos = Lottos.ofSize(purchaseAmount / 1000);
        outputView.printPurchasedLottos(lottos);
        return lottos;
    }
}
