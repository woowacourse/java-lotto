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
        int inputPurchaseAmount = inputView.askPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputPurchaseAmount);
        Lottos lottos = issueLottos(purchaseAmount.getMoney());

        List<Integer> winningNumbers = inputView.askWinningNumbers();
        int bonusNumber = inputView.askBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        List<WinningCounter> winningCounters = matchAndCountWinningLottos(winningLotto, lottos);
        calculateYield(purchaseAmount.getMoney(), winningCounters);
    }

    private Lottos issueLottos(int purchaseAmount) {
        Lottos lottos = Lottos.create(purchaseAmount);

        int quantity = lottos.getQuantity();
        outputView.printLottoQuantity(quantity);

        lottos.getLottos()
                .forEach(lotto -> outputView.printLotto(lotto.getBallNumbers()));

        return lottos;
    }

    private List<WinningCounter> matchAndCountWinningLottos(WinningLotto winningLotto, Lottos lottos) {
        List<Matcher> matchers = lottos.getLottos().stream()
                .map(lotto -> Matcher.count(winningLotto, lotto))
                .toList();

        List<WinningCounter> winningCounters = WinningCounter.count(matchers);
        outputView.printWinningStatistics(winningCounters);

        return winningCounters;
    }

    private void calculateYield(int purchaseAmount, List<WinningCounter> winningCounters) {
        Yield yield = Yield.calculate(purchaseAmount, winningCounters);
        outputView.printYield(yield.getYield());
    }
}
