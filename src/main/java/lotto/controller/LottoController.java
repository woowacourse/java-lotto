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

        Lottos manualLottos = Lottos.createByManual(new ArrayList<>());
        if (quantityOfManual > 0) {
            manualLottos = inputManualNumbers(quantityOfManual);
        }
        final Lottos autoLottos = Lottos.createByAuto(money.getQuantityOfAuto(quantityOfManual));

        OutputView.printInitResult(manualLottos, autoLottos);
        return Lottos.of(manualLottos.getLottos(), autoLottos.getLottos());
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

    private Lottos inputManualNumbers(int quantity) {
        OutputView.printManualNumbersMessage();
        final List<Lotto> lottos = new ArrayList<>();

        do {
            final Optional<Lotto> lotto = getValidManualLotto();
            lotto.ifPresent(lottos::add);
        } while (lottos.size() < quantity);
        return Lottos.createByManual(lottos);
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
