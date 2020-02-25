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
        try {
            Money money = new Money(InputView.getMoneyForLotto());
            Lottos lottos = LottosFactory.createLottosAuto(money);
            OutputView.printPurchasedLottos(lottos.getAmountOfLottos(), lottos);

            WinningInformation winningInformation = getWinningInformation();

            ResultStatistic result = ResultStatistic.calculate(lottos, winningInformation);
            OutputView.printResultStatistic(result, money);
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
        }
	}

	private static WinningInformation getWinningInformation() {
        Lotto winningLotto = Lotto.createLottoManual(InputView.getWinningLotto());
        LottoNumber bonus = LottoNumber.of(InputView.getBonusLottoNumber());
        return new WinningInformation(winningLotto, bonus);
	}
}
