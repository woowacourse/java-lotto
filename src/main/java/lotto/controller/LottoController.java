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
		MoneyForLotto moneyForLotto = InputView.getMoneyForLotto();
		int amountOfLottos = moneyForLotto.calculateAmountOfLottos();
		Lottos lottos = LottosFactory.createLottosAuto(amountOfLottos);

		OutputView.printPurchasedLottos(amountOfLottos, lottos);

		WinningLotto winningLotto = InputView.getWinningLotto();
		BonusLottoNumber bonusLottoNumber = InputView.getBonusLottoNumber(winningLotto);

		ResultStatistic result = ResultStatistic.calculate(lottos, winningLotto, bonusLottoNumber);
		OutputView.printResultStatistic(result, moneyForLotto);
	}
}
