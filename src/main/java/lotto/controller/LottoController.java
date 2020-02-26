package lotto.controller;

import lotto.domain.lotto.*;
import lotto.domain.lottonumber.BonusLottoNumber;
import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.money.InvalidMoneyForLottoException;
import lotto.domain.money.MoneyForLotto;
import lotto.domain.result.ResultStatistic;
import lotto.util.StringUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * 로또 실행을 담당, View와 Domain을 연결, 예외처리 담당
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
