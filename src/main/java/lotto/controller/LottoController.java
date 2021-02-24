package lotto.controller;

import lotto.domain.*;

import java.util.List;
import java.util.Scanner;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.printLottoPurchaseSummaryBasedOn;
import static lotto.view.OutputView.printResultStatisticsBasedOn;

public class LottoController {
	public void start(Scanner scanner) {
		LottoMachine lottoMachine = createLottoMachine(scanner);

		Lottos lottos = createLottosBy(scanner, lottoMachine);
		printLottoPurchaseSummaryBasedOn(lottos, lottoMachine);

		List<Rank> results = getResultsOf(scanner, lottos);
		Money moneyInvested = lottoMachine.getMoney();
		LottoStatistics lottoStatistics = new LottoStatistics(results, moneyInvested);
		printResultStatisticsBasedOn(lottoStatistics);
	}

	private LottoMachine createLottoMachine(Scanner scanner) {
		int moneyInput = takeMoneyInput(scanner);
		Money money = new Money(moneyInput);

		int manualLottoQuantityInput = takeManualLottoQuantityInput(scanner);
		LottoQuantity manualLottoQuantity = new LottoQuantity(manualLottoQuantityInput);

		return new LottoMachine(money, manualLottoQuantity);
	}

	private Lottos createLottosBy(Scanner scanner, LottoMachine lottoMachine) {
		List<int[]> manualLottoNumbersInput = takeManualLottoNumbersInput(scanner, lottoMachine);
		return lottoMachine.createLottosFrom(manualLottoNumbersInput);
	}

	private List<Rank> getResultsOf(Scanner scanner, Lottos lottos) {
		WinningLotto winningLotto = createWinningLotto(scanner);
		return lottos.getResultsBasedOn(winningLotto);
	}

	private WinningLotto createWinningLotto(Scanner scanner) {
		int[] winningNumbersInput = takeWinningNumbersInput(scanner);
		ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(winningNumbersInput);
		Lotto winningLotto = manualLottoGenerator.createLotto();

		int bonusNumberInput = takeBonusNumberInput(scanner);
		LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);

		return new WinningLotto(winningLotto, bonusNumber);
	}
}
