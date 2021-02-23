package lotto.controller;

import lotto.domain.*;

import java.util.List;
import java.util.Scanner;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.showLottos;
import static lotto.view.OutputView.showResultStatistics;

public class LottoController {
	//TODO
	// lotto machine 만들고 생산에 대한 로직 담당 (lottoQuantity를 인자로?)
	public void tryLotto(Scanner scanner) {
		LottoMachine lottoMachine = new LottoMachine(
				new Money(takeMoneyInput(scanner)),
				new LottoQuantity(takeManualLottoQuantityInput(scanner)));

		Lottos lottos = lottoMachine.createLottosFrom(takeManualLottoNumbersInput(scanner, lottoMachine));
		showLottos(lottos, lottoMachine);

		List<Rank> results = lottos.getResults(getWinningLotto(scanner));
		showResultStatistics(new LottoStatistics(results, lottoMachine.getMoney()));
	}

	//TODO List<int[]>뿐만 아니라 int[]도 받을 수 있게 할까?
	private WinningLotto getWinningLotto(Scanner scanner) {
		return new WinningLotto(
				new ManualLottoGenerator(takeWinningNumbersInput(scanner)).createLotto(),
				new LottoNumber(takeBonusNumberInput(scanner)));
	}
}
