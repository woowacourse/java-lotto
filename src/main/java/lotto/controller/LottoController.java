package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.LottosFactory;
import lotto.domain.Money;
import lotto.domain.ResultStatistic;
import lotto.domain.WinningInformation;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * LottoController.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class LottoController {
	public static void run() {
		Money money = getMoneyForLotto();
		int amountOfLottos = money.calculateAmountOfLottos();

		Lottos lottos = LottosFactory.createLottosAuto(money);
		OutputView.printPurchasedLottos(amountOfLottos, lottos);

		WinningInformation winningInformation = getWinningInformation();

		ResultStatistic result = ResultStatistic.calculate(lottos, winningInformation);
		OutputView.printResultStatistic(result, money);
	}

	private static Money getMoneyForLotto() {
		try {
			return new Money(InputView.getMoneyForLotto());
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
			return getMoneyForLotto();
		}
	}

	private static WinningInformation getWinningInformation() {
		try {
            Lotto winningLotto = Lotto.createLottoManual(InputView.getWinningLotto());
			LottoNumber bonus = LottoNumber.of(InputView.getBonusLottoNumber());
			return new WinningInformation(winningLotto, bonus);
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
			return getWinningInformation();
		}
	}
}
