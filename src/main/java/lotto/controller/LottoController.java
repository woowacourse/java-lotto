package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

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
		MoneyForLotto moneyForLotto = getMoneyForLotto();
		int amountOfLottos = moneyForLotto.calculateAmountOfLottos();
		Lottos lottos = LottosFactory.createLottosAuto(amountOfLottos);
		OutputView.printPurchasedLottos(amountOfLottos, lottos);
		WinningLotto winningLotto = getWinningLotto();
		BonusLottoNumber bonusLottoNumber = getBonusLottoNumber(winningLotto);

		ResultStatistic result = ResultStatistic.calculate(lottos, winningLotto, bonusLottoNumber);
		OutputView.printResultStatistic(result, moneyForLotto);
	}

	private static MoneyForLotto getMoneyForLotto() {
		try {
			return new MoneyForLotto(InputView.getMoneyForLotto());
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
			return getMoneyForLotto();
		}
	}

	private static WinningLotto getWinningLotto() {
		try {
			return (WinningLotto) LottoFactory.createLottoManual(
				LottoType.WINNING_LOTTO,
				InputView.getWinningLotto()
			);
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
			return getWinningLotto();
		}
	}

	private static BonusLottoNumber getBonusLottoNumber(WinningLotto winningLotto) {
		try {
			return new BonusLottoNumber(InputView.getBonusLottoNumber(), winningLotto);
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
			System.out.println("여기");
			return getBonusLottoNumber(winningLotto);
		}
	}
}
