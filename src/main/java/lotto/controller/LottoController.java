package lotto.controller;

import lotto.domain.*;
import lotto.util.StringUtils;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.domain.LottoFactory;

/**
 * 클래스 이름 : LottoController.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class LottoController {
	public static void run() {
		MoneyForLotto moneyForLotto = receiveMoneyForLotto();
		int amountOfLottos = moneyForLotto.calculateAmountOfLottos();
		Lottos lottos = LottosFactory.createLottosAuto(amountOfLottos);
		OutputView.printPurchasedLottos(amountOfLottos, lottos);

		WinningLotto winningLotto = receiveWinningLotto();
		BonusLottoNumber bonusLottoNumber = receiveBonusLottoNumber(winningLotto);

		ResultStatistic result = ResultStatistic.calculate(lottos, winningLotto, bonusLottoNumber);
		OutputView.printResultStatistic(result, moneyForLotto);
	}

	private static MoneyForLotto receiveMoneyForLotto() {
		try {
			return new MoneyForLotto(InputView.getMoneyForLotto());
		} catch (InvalidMoneyForLottoException | NullPointerException e) {
			OutputView.printExceptionMessage(e);
			return receiveMoneyForLotto();
		}
	}

	private static WinningLotto receiveWinningLotto() {
		try {
			String inputWinningLotto = InputView.getWinningLotto();
			return (WinningLotto) LottoFactory.createLottoManual(
					LottoType.WINNING_LOTTO,
					StringUtils.splitIntoLottoNumbers(inputWinningLotto)
			);
		} catch (InvalidLottoException | NullPointerException | IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return receiveWinningLotto();
		}
	}

	private static BonusLottoNumber receiveBonusLottoNumber(WinningLotto winningLotto) {
		try {
			String inputBonusLottoNumber = InputView.getBonusLottoNumber();
			BonusLottoNumber.validateBonusLottoNumber(inputBonusLottoNumber, winningLotto);
			return new BonusLottoNumber(inputBonusLottoNumber);
		} catch (InvalidLottoNumberException | NullPointerException e) {
			OutputView.printExceptionMessage(e);
			return receiveBonusLottoNumber(winningLotto);
		}
	}
}
