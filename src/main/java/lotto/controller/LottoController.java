package lotto.controller;

import lotto.domain.Money;
import lotto.domain.ticket.Lotto;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.Lottos;
import lotto.domain.production.LottoMachine;
import lotto.domain.production.LottoQuantity;
import lotto.domain.production.ManualLottoGenerator;
import lotto.domain.result.LottoStatistics;
import lotto.domain.result.Rank;
import lotto.domain.result.WinningLotto;

import java.util.List;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.printLottoPurchaseSummaryBasedOn;
import static lotto.view.OutputView.printResultStatisticsBasedOn;

public class LottoController {
	public void start() {
		LottoMachine lottoMachine = createLottoMachine();

		Lottos lottos = createLottosBy(lottoMachine);
		printLottoPurchaseSummaryBasedOn(lottos, lottoMachine);

		LottoStatistics lottoStatistics = createLottoStatisticsBasedOn(lottos, lottoMachine);
		printResultStatisticsBasedOn(lottoStatistics);

		closeScanner();
	}

	private LottoMachine createLottoMachine() {
		int moneyInput = takeMoneyInput();
		Money money = new Money(moneyInput);

		int manualLottoQuantityInput = takeManualLottoQuantityInput();
		LottoQuantity manualLottoQuantity = new LottoQuantity(manualLottoQuantityInput);

		return new LottoMachine(money, manualLottoQuantity);
	}

	private Lottos createLottosBy(LottoMachine lottoMachine) {
		List<int[]> manualLottoNumbersInput = takeManualLottoNumbersInput(lottoMachine);
		return lottoMachine.createLottosFrom(manualLottoNumbersInput);
	}

	private LottoStatistics createLottoStatisticsBasedOn(Lottos lottos, LottoMachine lottoMachine) {
		List<Rank> results = getResultsOf(lottos);
		Money moneyInvested = lottoMachine.getMoney();
		return new LottoStatistics(results, moneyInvested);
	}

	private List<Rank> getResultsOf(Lottos lottos) {
		WinningLotto winningLotto = createWinningLotto();
		return lottos.getResultsBasedOn(winningLotto);
	}

	private WinningLotto createWinningLotto() {
		int[] winningNumbersInput = takeWinningNumbersInput();
		ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(winningNumbersInput);
		Lotto winningLotto = manualLottoGenerator.createLotto();

		int bonusNumberInput = takeBonusNumberInput();
		LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);

		return new WinningLotto(winningLotto, bonusNumber);
	}
}
