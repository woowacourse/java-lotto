package controller;

import domain.lotto.Lottos;
import domain.lotto.PurchaseAmount;
import domain.winning.Matcher;
import domain.winning.WinningCounter;
import domain.winning.WinningLotto;
import domain.winning.Yield;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = purchase();
        Lottos lottos = issueLottos(purchaseAmount);

        WinningLotto winningLotto = announceWinningLotto();

        List<WinningCounter> winningCounters = matchAndCountWinning(winningLotto, lottos);
        calculateYield(purchaseAmount, winningCounters);
    }

    private PurchaseAmount purchase() {
        int inputPurchaseAmount = inputView.askPurchaseAmount();
        return new PurchaseAmount(inputPurchaseAmount);
    }

    private WinningLotto announceWinningLotto() {
        List<Integer> winningNumbers = inputView.askWinningNumbers();
        int bonusNumber = inputView.askBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lottos issueLottos(PurchaseAmount purchaseAmount) {
        int lottoQuantity = purchaseAmount.getLottoQuantity();
        outputView.printLottoQuantity(lottoQuantity);

        Lottos lottos = Lottos.create(purchaseAmount.getMoney());

        lottos.getLottos()
                .forEach(lotto -> outputView.printLotto(lotto.getBallNumbers()));

        return lottos;
    }

    private List<WinningCounter> matchAndCountWinning(WinningLotto winningLotto, Lottos lottos) {
        List<Matcher> matchers = lottos.getLottos().stream()
                .map(lotto -> Matcher.count(winningLotto, lotto))
                .toList();

        List<WinningCounter> winningCounters = WinningCounter.count(matchers);
        outputView.printWinningStatistics(winningCounters);

        return winningCounters;
    }

    private void calculateYield(PurchaseAmount purchaseAmount, List<WinningCounter> winningCounters) {
        Yield yield = Yield.calculate(purchaseAmount.getMoney(), winningCounters);
        outputView.printYield(yield.getYield());
    }
}
