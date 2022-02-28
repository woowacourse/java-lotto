package lotto.controller;

import java.util.Optional;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public Lottos initLottos(Money money) {
        final Lottos lottos = new Lottos(money);
        OutputView.printInitResult(lottos);
        return lottos;
    }

    public void play(Lottos lottos, WinningNumbers winningNumbers, Money money) {
        final Result result = lottos.getResult(winningNumbers);
        OutputView.printPlayResult(result, money);
    }

    private int inputQuantityOfManual(Money money) {
        Optional<Integer> quantityOfManual;

        do {
            quantityOfManual = getValidQuantityOfManual(money);
        } while (quantityOfManual.isEmpty());
        return quantityOfManual.get();
    }

    private Optional<Integer> getValidQuantityOfManual(Money money) {
        try {
            final int quantityOfManual = InputView.inputManualCount();
            money.validateQuantityOfManual(quantityOfManual);

            return Optional.of(quantityOfManual);
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }
}
