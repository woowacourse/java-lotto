package lotto.controller;

import lotto.domain.Factory.AutoLottosFactory;
import lotto.domain.Factory.LottosFactory;
import lotto.domain.Factory.ManualLottosFactory;
import lotto.domain.*;
import lotto.exceptions.LottoMoneyIllegalArgumentException;
import lotto.exceptions.LottoNumberIllegalArgumentException;
import lotto.exceptions.PurchaseManualLottoIllegalArgumentException;
import lotto.exceptions.WinningLottoIllegalArgumentException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
	public static void run() {
		LottoMoney lottoMoney = prepareLotto();

		LottoMoney manualLottoMoney = prepareManualLottoMoney(lottoMoney);
		LottoMoney autoLottoMoney
				= prepareAutoLottoMoney(lottoMoney, manualLottoMoney);

		PurchasedLottos purchasedLottos
				= purchaseLottos(manualLottoMoney, autoLottoMoney);

		WinningLotto winningLotto = prepareWinningLotto();

		produceLottoResult(lottoMoney, purchasedLottos, winningLotto);
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

	private static PurchasedLottos purchaseLottos(
			LottoMoney manualLottoMoney, LottoMoney autoLottoMoney) {
		OutputView.printInputManualLottoNumbersMessage();

		PurchasedLottos manualPurchasedLottos = purchaseLotto(manualLottoMoney,
				new ManualLottosFactory());
		PurchasedLottos autoPurchasedLottos = purchaseLotto(autoLottoMoney,
				new AutoLottosFactory());

		OutputView.printPurchasedLottos(manualPurchasedLottos, autoPurchasedLottos);
		return manualPurchasedLottos.add(autoPurchasedLottos);
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
			PurchasedLottos purchasedLottos,
			WinningLotto winningLotto) {
		LottoResult lottoResult = LottoResult.of(purchasedLottos, winningLotto);
		OutputView.printLottoResult(lottoResult);
		OutputView.printEarningRate(lottoResult.calculateEarningPercentage(lottoMoney));
	}

	private static PurchasedLottos purchaseLotto(
			LottoMoney manualLottoMoney, LottosFactory lottosFactory) {
		return lottosFactory.create(manualLottoMoney);
	}

	private static LottoMoney prepareLotto() {
		LottoMoney lottoMoney = prepareLottoMoney();
		OutputView.printCountOfPurchasedLottos(lottoMoney);
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
			return WinningLotto.of(prepareWinningNumbers(), prepareBonusNumber());
		} catch (WinningLottoIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static Lotto prepareWinningNumbers() {
		Lotto winningNumber;
		do {
			winningNumber = prepareWinningNumbersIfValid();
		} while (winningNumber == null);

		return winningNumber;
	}

	private static Lotto prepareWinningNumbersIfValid() {
		try {
			return Lotto.of(InputView.inputWinningNumbers());
		} catch (IllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static LottoNumber prepareBonusNumber() {
		LottoNumber bonusNumber;
		do {
			bonusNumber = prepareBonusNumberIfValid();
		} while (bonusNumber == null);

		return bonusNumber;
	}

	private static LottoNumber prepareBonusNumberIfValid() {
		try {
			return LottoNumber.of(InputView.inputBonusNumber());
		} catch (LottoNumberIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}
}
