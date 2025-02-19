package controller;

import domain.BonusNumber;
import domain.Lotto;
import domain.LottoRank;
import domain.LottoStore;
import domain.Money;
import domain.WinningNumber;
import domain.WinningProfit;
import domain.WinningResult;
import dto.WinningRecipeGroup;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
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

    public void playLotto() {
        final Money money = requestMoney();

        final LottoStore lottoStore = new LottoStore(new RandomNumbersGenerator(), money);
        final List<Lotto> lottos = lottoStore.issueLottos();
        outputView.printUserLottos(lottos);

        final WinningNumber winningNumber = requestWinningNumber();
        final WinningResult winningResult = new WinningResult(winningNumber, lottos);

        final Map<LottoRank, Integer> countedWinningResult = winningResult.countWinningResult();
        outputView.printWinningResult(WinningRecipeGroup.of(countedWinningResult));

        final WinningProfit winningProfit = new WinningProfit(countedWinningResult);
        outputView.printWinningProfit(winningProfit.calculateProfitRate(money.getAmount()));
    }

    private WinningNumber requestWinningNumber() {
        final Lotto winningLotto = requestWinningLotto();
        return tryCatchLoopTemplate(() -> {
            outputView.printAskInputBonusNumber();
            final BonusNumber bonusNumber = inputView.readBonusNumber();
            return new WinningNumber(winningLotto, bonusNumber);
        });
    }

    private Lotto requestWinningLotto() {
        return tryCatchLoopTemplate(() -> {
            outputView.printAskInputWiningLotto();
            final List<Integer> numbers = inputView.readWinningNumbers();
            return new Lotto(numbers);
        });
    }

    private Money requestMoney() {
        return tryCatchLoopTemplate(() -> {
            outputView.printAskInputMoney();
            return inputView.readMoney();
        });
    }

    private <T> T tryCatchLoopTemplate(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
