package lotto.controller;

import java.util.List;

import lotto.domain.lotto.*;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.money.MoneyForLotto;
import lotto.domain.result.ResultStatistic;
import lotto.util.StringUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * 로또 실행을 담당, View와 Domain을 연결
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class LottoController {
	private final int MINIMUM_COUNT = 0;

	public void run() {
		MoneyForLotto moneyForLotto = new MoneyForLotto(InputView.getMoneyForLotto());
		LottoCount lottoCount = new LottoCount(moneyForLotto.calculateAmountOfLottos(), InputView.getManualLottoCount());

		Lottos purchasedLottos = receivePurchasedLottos(lottoCount);
		OutputView.printPurchasedLottos(lottoCount, purchasedLottos);

		String inputWinningLottoNumbers = InputView.getWinningLotto();
		String inputBonusLottoNumber = InputView.getBonusLottoNumber();
		WinningLotto winningLotto = new WinningLotto(inputWinningLottoNumbers, inputBonusLottoNumber);

		ResultStatistic result = ResultStatistic.calculate(purchasedLottos, winningLotto);
		OutputView.printResultStatistic(result, moneyForLotto);
	}

	private Lottos receivePurchasedLottos(LottoCount lottoCount) {
		List<String> manualLottoNumbers = InputView.getManualLottoNumbers(lottoCount.getManualLottoCount());

		if (isManualAndAuto(lottoCount)) {
			Lottos manualLottos = LottosFactory.createManualLottos(manualLottoNumbers);
			Lottos autoLottos = LottosFactory.createAutoLottos(lottoCount.getAutoLottoCount());
			return manualLottos.add(autoLottos);
		}
		if (isOnlyManual(lottoCount)) {
			return LottosFactory.createManualLottos(manualLottoNumbers);
		}
		return LottosFactory.createAutoLottos(lottoCount.getAutoLottoCount());
	}

	private boolean isOnlyManual(LottoCount lottoCount) {
		return lottoCount.getAutoLottoCount() == MINIMUM_COUNT;
	}

	private boolean isManualAndAuto(LottoCount lottoCount) {
		return lottoCount.getManualLottoCount() != MINIMUM_COUNT && lottoCount.getAutoLottoCount() != MINIMUM_COUNT;
	}
}
