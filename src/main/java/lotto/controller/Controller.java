package lotto.controller;

import lotto.domain.Factory.AutoLottosFactory;
import lotto.domain.Factory.LottosFactory;
import lotto.domain.Factory.ManualLottosFactory;
import lotto.domain.*;
import lotto.exceptions.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
	public static void run() {
		LottoMoney lottoMoney = prepareLotto();

		LottoMoney manualLottoMoney = prepareManualLottoMoney(lottoMoney);
		LottoMoney autoLottoMoney
				= prepareAutoLottoMoney(lottoMoney, manualLottoMoney);

		Lottos lottos
				= purchaseLottos(manualLottoMoney, autoLottoMoney);

		WinningLotto winningLotto = prepareWinningLotto();

		produceLottoResult(lottoMoney, lottos, winningLotto);
	}

	private static LottoMoney prepareAutoLottoMoney(
			LottoMoney lottoMoney, LottoMoney manualLottoMoney) {

		LottoMoney autoLottoMoney;
		do {
			autoLottoMoney = prepareAutoLottoIfValid(
					lottoMoney, manualLottoMoney);
		} while (autoLottoMoney == null);
		return autoLottoMoney;
	}

	private static LottoMoney prepareAutoLottoIfValid(
			LottoMoney lottoMoney, LottoMoney manualLottoMoney) {
		try {
			return lottoMoney.subtract(manualLottoMoney);
		} catch (PurchaseManualLottoIllegalArgumentException e) {
			return null;
		}
	}

	private static Lottos purchaseLottos(
			LottoMoney manualLottoMoney, LottoMoney autoLottoMoney) {
		OutputView.printInputManualLottoNumbersMessage();

		Lottos manualLottos = purchaseLotto(manualLottoMoney,
				new ManualLottosFactory());
		Lottos autoLottos = purchaseLotto(autoLottoMoney,
				new AutoLottosFactory());

		OutputView.printLottos(manualLottos, autoLottos);
		return manualLottos.add(autoLottos);
	}

	private static LottoMoney prepareManualLottoMoney(
			LottoMoney lottoMoney) {

		LottoMoney manualLottoMoney;
		do {
			manualLottoMoney = prepareManualLottoMoneyIfValid(lottoMoney);
		} while (manualLottoMoney == null);
		return manualLottoMoney;
	}

	private static LottoMoney prepareManualLottoMoneyIfValid(
			LottoMoney lottoMoney) {

		int manualLottoNumber = InputView.inputManualLottoNumber();

		if (lottoMoney.canNotPurchase(manualLottoNumber)) {
			OutputView.printWhenManualMoneyIsMoreThanTotalMoney();
			return null;
		}

		try {
			return LottoMoney.ofLottoCount(manualLottoNumber);
		} catch (PurchaseManualLottoIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static void produceLottoResult(
			LottoMoney lottoMoney,
			Lottos lottos,
			WinningLotto winningLotto) {
		LottoResult lottoResult = LottoResult.of(lottos, winningLotto);
		OutputView.printLottoResult(lottoResult);
		OutputView.printEarningRate(lottoResult.calculateEarningPercentage(lottoMoney));
	}

	private static Lottos purchaseLotto(
			LottoMoney manualLottoMoney, LottosFactory lottosFactory) {
		return lottosFactory.create(manualLottoMoney);
	}

	private static LottoMoney prepareLotto() {
		LottoMoney lottoMoney = prepareLottoMoney();
		OutputView.printCountOfLottos(lottoMoney);
		return lottoMoney;
	}

	private static LottoMoney prepareLottoMoney() {
		LottoMoney lottoMoney;
		do {
			lottoMoney = prepareLottoMoneyIfValid();
		} while (lottoMoney == null);

		return lottoMoney;
	}

	private static LottoMoney prepareLottoMoneyIfValid() {
		try {
			return LottoMoney.of(InputView.inputPurchaseMoney());
		} catch (LottoMoneyIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static WinningLotto prepareWinningLotto() {
		WinningLotto winningLotto;
		do {
			winningLotto = prepareWinningLottoIfValid();
		} while (winningLotto == null);

		return winningLotto;
	}

	private static WinningLotto prepareWinningLottoIfValid() {
		try {
			return WinningLotto.of(InputView.inputWinningNumbers(),
					InputView.inputBonusNumber());
		} catch (WinningLottoIllegalArgumentException | LottoIllegalArgumentException |
				LottoNumberIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}
}
