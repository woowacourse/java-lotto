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

        Lottos manualLottos = buy(new ManualLottoGenerator(null), manualLottoQuantity);

        Money moneyLeftAfterBuyingManualLotto = money.calculateMoneyLeft(manualLottoQuantity);
        Lottos autoLottos = buy(new AutomaticLottoGenerator(), moneyLeftAfterBuyingManualLotto.calculateAffordableLottoQuantity());

        Lottos totalLottos = manualLottos.merge(autoLottos);
        OutputView.showLottos(totalLottos);

        List<Rank> results = totalLottos.getResults(getWinningLotto(scanner));

        OutputView.showResultStatistics(new LottoStatistics(results, money));
    }

    public Lottos buy(LottoGenerator lottoGenerator, int lottoQuantity) {
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
