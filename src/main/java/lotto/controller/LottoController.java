package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoController {
	//TODO
	// lotto quantity에 대한 클래스 만들고 에서 음수 인지 검증?
	// lotto machine 만들고 생산에 대한 로직 담당 (lottoQuantity를 인자로?)
	public void tryLotto(Scanner scanner) {
		Money money = new Money(InputView.takeMoneyInput(scanner));
		LottoQuantity manualLottoQuantity = new LottoQuantity(InputView.takeManualLottoQuantityInput(scanner));
		money.validateAffordabilityOf(manualLottoQuantity);
		List<int[]> manualLottoNumbersSequence = InputView.takeManualLottoNumbersInput(scanner, manualLottoQuantity);

		Lottos lottos = buyLottos(manualLottoNumbersSequence, manualLottoQuantity, money);
		OutputView.showLottos(lottos, manualLottoQuantity);

		List<Rank> results = lottos.getResults(getWinningLotto(scanner));
		OutputView.showResultStatistics(new LottoStatistics(results, money));
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

	private WinningLotto getWinningLotto(Scanner scanner) {
		List<int[]> winningNumbers = InputView.takeWinningNumbersInput(scanner);
		int bonusNumber = InputView.takeBonusNumberInput(scanner);
		return new WinningLotto(new ManualLottoGenerator(winningNumbers).createLotto(), new LottoNumber(bonusNumber));
	}
}
