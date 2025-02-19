package lotto.controller;

import static lotto.view.PromptMessageContainer.ENTER_BONUS_NUMBER;
import static lotto.view.PromptMessageContainer.ENTER_PURCHASE_AMOUNT;
import static lotto.view.PromptMessageContainer.ENTER_WINNING_NUMBERS;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
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
        final Lottos lottos = issueLotto();
        final WinningLotto winningLotto = createWinningLotto();
        checkWinningResult(lottos, winningLotto);
        inputView.close();
    }

    private Lottos issueLotto() {
        final int purchaseAmount = requestPurchaseAmount();
        Lottos lottos = Lottos.ofAmount(purchaseAmount);
        outputView.printPurchasedLottos(lottos);
        return lottos;
    }

    private WinningLotto createWinningLotto() {
        final List<Integer> winningNumbers = requestWinningNumbers();
        final int bonusNumber = requestBonusNumber();
        return WinningLotto.of(Lotto.of(winningNumbers), bonusNumber);
    }

    private void checkWinningResult(final Lottos lottos, final WinningLotto winningLotto) {
        List<Rank> ranks = lottos.calculateRanks(winningLotto);
        double earningRate = lottos.calculateEarningRate(ranks);

        outputView.printLottoResult(Rank.count(ranks), earningRate);
    }

    private int requestPurchaseAmount() {
        String rawPurchaseAmount = inputView.read(ENTER_PURCHASE_AMOUNT);
        return NumberParser.parse(rawPurchaseAmount);
    }

    private List<Integer> requestWinningNumbers() {
        String rawWinningNumber = inputView.read(ENTER_WINNING_NUMBERS);
        return NumberParser.parseFromCSV(rawWinningNumber);
    }

    private int requestBonusNumber() {
        final String rawBonusNumber = inputView.read(ENTER_BONUS_NUMBER);
        return NumberParser.parse(rawBonusNumber);
    }
}
