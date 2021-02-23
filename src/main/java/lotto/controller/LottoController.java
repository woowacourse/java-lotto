package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.showLottos;
import static lotto.view.OutputView.showResultStatistics;

public class LottoController {
	//TODO
	// lotto machine 만들고 생산에 대한 로직 담당 (lottoQuantity를 인자로?)
	public void tryLotto(Scanner scanner) {
		Money money = new Money(takeMoneyInput(scanner));
		LottoQuantity manualLottoQuantity = new LottoQuantity(takeManualLottoQuantityInput(scanner));
		money.validateAffordabilityOf(manualLottoQuantity);
		List<int[]> manualLottoNumbersSequence = takeManualLottoNumbersInput(scanner, manualLottoQuantity);

		Lottos lottos = buyLottos(manualLottoNumbersSequence, manualLottoQuantity, money);
		showLottos(lottos, manualLottoQuantity);

		List<Rank> results = lottos.getResults(getWinningLotto(scanner));
		showResultStatistics(new LottoStatistics(results, money));
	}

	private Lottos buyLottos(List<int[]> manualLottoNumbersSequence, LottoQuantity manualLottoQuantity, Money money) {
		Lottos manualLottos = buy(new ManualLottoGenerator(manualLottoNumbersSequence), manualLottoQuantity);
		Money moneyLeft = money.getChangeAfterBuying(manualLottoQuantity);

		Lottos autoLottos = buy(new AutomaticLottoGenerator(), moneyLeft.getAffordableLottoQuantity());

		return manualLottos.merge(autoLottos);
	}

	private Lottos buy(LottoGenerator lottoGenerator, LottoQuantity lottoQuantity) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < lottoQuantity.getLottoQuantity(); i++) {
			lottos.add(lottoGenerator.createLotto());
		}
		return new Lottos(lottos);
	}

	//TODO List<int[]>뿐만 아니라 int[]도 받을 수 있게 할까?
	private WinningLotto getWinningLotto(Scanner scanner) {
		List<int[]> winningNumbers = takeWinningNumbersInput(scanner);
		int bonusNumber = takeBonusNumberInput(scanner);
		return new WinningLotto(new ManualLottoGenerator(winningNumbers).createLotto(), new LottoNumber(bonusNumber));
	}
}
