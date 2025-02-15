package controller;

import domain.Lottos;
import domain.Matcher;
import domain.PurchaseAmount;
import domain.WinningCount;
import domain.WinningLotto;
import domain.WinningStatistics;
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

        List<WinningCount> winningCounts = matchAndCountWinningLottos(winningLotto, lottos);
        calculateYield(purchaseAmount.getMoney(), winningCounts);
    }

    private Lottos issueLottos(int purchaseAmount) {
        Lottos lottos = Lottos.create(purchaseAmount);

        int quantity = lottos.getQuantity();
        outputView.printLottoQuantity(quantity);

        lottos.getLottos()
                .forEach(lotto -> outputView.printLotto(lotto.getBallNumbers()));

        return lottos;
    }

    private List<WinningCount> matchAndCountWinningLottos(WinningLotto winningLotto, Lottos lottos) {
        List<Matcher> matchers = lottos.getLottos().stream()
                .map(lotto -> Matcher.count(winningLotto, lotto))
                .toList();

        List<WinningCount> winningCounts = WinningStatistics.calculateWinningCount(matchers);
        outputView.printWinningStatistics(winningCounts);

        return winningCounts;
    }

    private void calculateYield(int purchaseAmount, List<WinningCount> winningCounts) {
        double yield = WinningStatistics.calculateYield(purchaseAmount, winningCounts);
        outputView.printYield(yield);
    }
}
