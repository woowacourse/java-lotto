package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public Lottos initLottos(Money money) {
        final int quantityOfManual = inputQuantityOfManual(money);

        List<Lotto> manualNumbers = new ArrayList<>();
        if (quantityOfManual > 0) {
            manualNumbers = inputManualNumbers(quantityOfManual);
        }

        final Lottos lottos = new Lottos(money, manualNumbers);
        OutputView.printInitResult(lottos);
        return lottos;
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

    private List<Lotto> inputManualNumbers(int quantity) {
        OutputView.printManualNumbersMessage();
        final List<Lotto> lottos = new ArrayList<>();

        do {
            final Optional<Lotto> lotto = getValidManualLotto();
            lotto.ifPresent(lottos::add);
        } while (lottos.size() < quantity);
        return lottos;
    }

    private Optional<Lotto> getValidManualLotto() {
        try {
            return Optional.of(Lotto.createByManual(InputView.inputNumbers()));
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }

    public void play(Lottos lottos, WinningNumbers winningNumbers, Money money) {
        final Result result = lottos.getResult(winningNumbers);
        OutputView.printPlayResult(result, money);
    }
}
