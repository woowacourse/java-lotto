package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
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
public class AutoLottoController {
	public static void run() {
        try {
            runWithoutExceptionCatch();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
        }
	}

    private static void runWithoutExceptionCatch() {
        LottoMoney money = new LottoMoney(InputView.getMoneyForLotto());
        Lottos lottos = Lottos.createLottosAuto(money);
        OutputView.printAutoPurchasedLottos(lottos.getAmountOfLottos(), lottos);

        WinningInformation winningInformation = readWinningInformation();

        ResultStatistic result = ResultStatistic.calculate(lottos, winningInformation);
        OutputView.printResultStatistic(result, money.getMoney());
    }

	private static WinningInformation readWinningInformation() {
        Lotto winningLotto = Lotto.createLottoManual(InputView.getWinningLotto());
        LottoNumber bonus = LottoNumber.of(InputView.getBonusLottoNumber());
        return new WinningInformation(winningLotto, bonus);
	}
}
