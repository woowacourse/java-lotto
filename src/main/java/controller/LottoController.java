package controller;

import domain.BonusNumber;
import domain.Lotto;
import domain.LottoRank;
import domain.LottoStore;
import domain.PurchaseAmount;
import domain.WinningNumber;
import domain.WinningProfit;
import domain.WinningResult;
import java.util.List;
import java.util.Map;
import utils.RandomNumbersGenerator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final PurchaseAmount purchaseAmount = requestPurchaseAmount();

        final LottoStore lottoStore = new LottoStore(new RandomNumbersGenerator(), purchaseAmount);
        final List<Lotto> lottos = lottoStore.issueLottos();
        outputView.printUserLottos(lottos);

        final WinningNumber winningNumber = requestWinningNumber();
        final WinningResult winningResult = new WinningResult(winningNumber, lottos);

        final Map<LottoRank, Integer> countedWinningResult = winningResult.countWinningResult();
        outputView.printWinningResult(countedWinningResult);

        final WinningProfit winningProfit = new WinningProfit(countedWinningResult);
        outputView.printWinningProfit(winningProfit.calculateProfitRate(purchaseAmount.getAmount()));
    }

    private PurchaseAmount requestPurchaseAmount() {
        outputView.printAskInputPurchaseAmount();
        return inputView.readPurchaseAmount();
    }

    private WinningNumber requestWinningNumber() {
        outputView.printAskInputWiningLotto();
        final List<Integer> numbers = inputView.readWinningNumbers();
        final Lotto winningLotto = new Lotto(numbers);
        outputView.printAskInputBonusNumber();
        final BonusNumber bonusNumber = inputView.readBonusNumber();
        return new WinningNumber(winningLotto, bonusNumber);
    }
}
