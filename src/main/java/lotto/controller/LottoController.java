package lotto.controller;

import lotto.domain.*;

import java.util.List;
import java.util.Scanner;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.printLottoPurchaseSummaryBasedOn;
import static lotto.view.OutputView.printResultStatisticsBasedOn;

public class LottoController {
	public void start(Scanner scanner) {
		LottoMachine lottoMachine = new LottoMachine(
				new Money(takeMoneyInput(scanner)),
				new LottoQuantity(takeManualLottoQuantityInput(scanner)));

		Lottos lottos = lottoMachine.createLottosFrom(takeManualLottoNumbersInput(scanner, lottoMachine));
		printLottoPurchaseSummaryBasedOn(lottos, lottoMachine);

		List<Rank> results = lottos.getResultsBasedOn(getWinningLotto(scanner));
		printResultStatisticsBasedOn(new LottoStatistics(results, lottoMachine.getMoney()));
	}

	private WinningLotto getWinningLotto(Scanner scanner) {
		return new WinningLotto(
				new ManualLottoGenerator(takeWinningNumbersInput(scanner)).createLotto(),
				new LottoNumber(takeBonusNumberInput(scanner)));
	}
}
