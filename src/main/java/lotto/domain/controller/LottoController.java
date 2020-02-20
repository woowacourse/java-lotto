package lotto.domain.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.Number;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;
import lotto.domain.model.LottoGame;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public void run() {
		PurchaseMoney money = new PurchaseMoney(InputView.getMoney());
		Lottos lottos = LottoFactory.create(money.parseToPiece());
		OutputView.printPieces(money.parseToPiece());
		OutputView.printLottos(lottos);
		createResult(money, lottos, createWinningLotto());
	}

	private WinningLotto createWinningLotto() {
		Lotto winningNumbers = LottoFactory.create(InputView.getWinningNumbers());
		Number bonusNumber = Number.of(InputView.getBonusNumber());
		return new WinningLotto(winningNumbers, bonusNumber);
	}

	private void createResult(PurchaseMoney money, Lottos lottos, WinningLotto winningLotto) {
		LottoGame game = new LottoGame(lottos, winningLotto, money);
		OutputView.printResult(game.getResult());
	}
}
