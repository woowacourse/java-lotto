package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoFactory;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class LottoController {
	public static void run() {
		MoneyForLotto moneyForLotto = InputView.getMoneyForLotto();
		int amountOfLottos = moneyForLotto.calculateAmountOfLottos();

		List<Lotto> paidLotto = new ArrayList<>();
		for (int index = 0; index < amountOfLottos; index++) {
			paidLotto.add(LottoFactory.createLottoAuto(LottoType.PAID_LOTTO));
		}
		Lottos lottos = new Lottos(paidLotto);

		OutputView.printPurchasedLottos(amountOfLottos, lottos);

		WinningLotto winningLotto = InputView.getWinningLotto();
		BonusLottoNumber bonusLottoNumber = InputView.getBonusLottoNumber(winningLotto);

		ResultStatistic result = ResultStatistic.calculate(lottos, winningLotto, bonusLottoNumber);
		OutputView.printResultStatistic(result, moneyForLotto);
	}
}
