package lotto.domain.controller;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.Number;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;
import lotto.domain.result.GameResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public void run() {
		PurchaseMoney money = new PurchaseMoney(InputView.getMoney());
		LottoCount count = new LottoCount(InputView.getManualCount(),money.parseToPiece());
		List<String> manualLottoInput = InputView.getManualLottos(count.getManualLotto());
		Lottos lottos = LottoFactory.create(manualLottoInput, count.getAutoLotto());

		OutputView.printPieces(count);
		OutputView.printLottos(lottos);

		createResult(money, lottos, createWinningLotto());
	}

	private WinningLotto createWinningLotto() {
		Lotto winningNumbers = LottoFactory.create(InputView.getWinningNumbers());
		Number bonusNumber = Number.of(InputView.getBonusNumber());
		return new WinningLotto(winningNumbers, bonusNumber);
	}

	private void createResult(PurchaseMoney money, Lottos lottos, WinningLotto winningLotto) {
		GameResult result = new GameResult(winningLotto, lottos);
		OutputView.printResult(result);
		OutputView.printProfit(result.getEarningMoney(money));
	}
}
