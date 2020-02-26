package lotto.controller;

import lotto.domain.lotto.*;
import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.money.InvalidMoneyForLottoException;
import lotto.domain.money.MoneyForLotto;
import lotto.domain.result.ResultStatistic;
import lotto.util.StringUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

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
		LottoCount lottoCount = receiveLottoCount(moneyForLotto);

		Lottos purchasedLottos = receivePurchasedLottos(lottoCount);
		OutputView.printPurchasedLottos(lottoCount, purchasedLottos);

		WinningLotto winningLotto = receiveWinningLotto();

		ResultStatistic result = ResultStatistic.calculate(purchasedLottos, winningLotto);
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

	private static LottoCount receiveLottoCount(MoneyForLotto moneyForLotto) {
		try {
			return new LottoCount(moneyForLotto.calculateAmountOfLottos(), InputView.getManualLottoCount());
		} catch (IllegalArgumentException | NullPointerException | InvalidLottoNumberException e) {
			OutputView.printExceptionMessage(e);
			return receiveLottoCount(moneyForLotto);
		}
	}

	private static Lottos receivePurchasedLottos(LottoCount lottoCount) {
		Lottos manualLottos = receiveManualLottos(lottoCount);
		Lottos autoLottos = LottosFactory.createAutoLottos(lottoCount.getAutoLottoCount());
		return manualLottos.add(autoLottos);
	}

	private static Lottos receiveManualLottos(LottoCount lottoCount) {
		try {
			List<String> manualLottoNumbers = InputView.getManualLottoNumbers(lottoCount.getManualLottoCount());
			return LottosFactory.createManualLottos(manualLottoNumbers);
		} catch (NullPointerException | IllegalArgumentException |  InvalidLottoNumberException | InvalidLottoException | InvalidLottosException e) {
			OutputView.printExceptionMessage(e);
			return receiveManualLottos(lottoCount);
		}
	}

	private static WinningLotto receiveWinningLotto() {
		try {
			List<LottoNumber> winningLottoNumbers = receiveWinningLottoNumbers();
			LottoNumber bonusLottoNumber = receiveBonusLottoNumber();
			return new WinningLotto(winningLottoNumbers, bonusLottoNumber);
		} catch (InvalidLottoNumberException e) {
			OutputView.printExceptionMessage(e);
			return receiveWinningLotto();
		}
	}

	private static List<LottoNumber> receiveWinningLottoNumbers() {
		try {
			String inputWinningLotto = InputView.getWinningLotto();
			return StringUtils.splitIntoLottoNumbers(inputWinningLotto);
		} catch (InvalidLottoNumberException | NullPointerException | IllegalArgumentException e) {
			OutputView.printExceptionMessage(e);
			return receiveWinningLottoNumbers();
		}
	}

	private static LottoNumber receiveBonusLottoNumber() {
		try {
			String inputBonusLottoNumber = InputView.getBonusLottoNumber();
			LottoNumber bonusLottoNumber = LottoNumber.of(LottoNumber.parseToInteger(inputBonusLottoNumber));
			return bonusLottoNumber;
		} catch (InvalidLottoNumberException | NullPointerException e) {
			OutputView.printExceptionMessage(e);
			return receiveBonusLottoNumber();
		}
	}
}
