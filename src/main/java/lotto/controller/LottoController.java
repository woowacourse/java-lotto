package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoController {
    public void tryLotto(Scanner scanner) {
        Money money = new Money(InputView.takeMoneyInput(scanner));

        int manualLottoQuantity = InputView.takeManualLottoQuantityInput(scanner);
        money.validateAffordability(manualLottoQuantity);
        List<int[]> manualLottoNumbersSequence = InputView.takeManualLottoNumbersInput(scanner, manualLottoQuantity);

        Lottos lottos = buyLottos(manualLottoNumbersSequence, manualLottoQuantity, money);
        OutputView.showLottos(lottos, manualLottoQuantity);

        List<Rank> results = lottos.getResults(getWinningLotto(scanner));
        OutputView.showResultStatistics(new LottoStatistics(results, money));
    }

    private Lottos buyLottos(List<int[]> manualLottoNumbersSequence, int manualLottoQuantity, Money money) {
        Lottos manualLottos = buy(new ManualLottoGenerator(manualLottoNumbersSequence), manualLottoQuantity);
        Money moneyLeftAfterBuyingManualLottos = money.getChange(manualLottoQuantity);

        Lottos autoLottos = buy(new AutomaticLottoGenerator(), moneyLeftAfterBuyingManualLottos.getAffordableLottoQuantity());

        return manualLottos.merge(autoLottos);
    }

    private Lottos buy(LottoGenerator lottoGenerator, int lottoQuantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoQuantity; i++) {
            lottos.add(lottoGenerator.createLotto());
        }
        return new Lottos(lottos);
    }

    private WinningLotto getWinningLotto(Scanner scanner) {
        List<int[]> winningNumbers = InputView.takeWinningNumbersInput(scanner);
        int bonusNumber = InputView.takeBonusNumberInput(scanner);
        return new WinningLotto(new ManualLottoGenerator(winningNumbers).createLotto(), new LottoNumber(bonusNumber));
    }
}
