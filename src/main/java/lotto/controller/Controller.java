package lotto.controller;

import lotto.domain.*;
import lotto.domain.SerialLottoNumberFactory.AutoLottoFactory;
import lotto.exceptions.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
	public static void run() {
		LottoMoney lottoMoney = prepareLotto();

		LottoMoney manualTicketMoney = createManualTicketMoney(lottoMoney);
		LottoMoney autoTicketMoney
				= createAutoTicketMoney(lottoMoney, manualTicketMoney);

		PurchasedLottos purchasedLottos
				= purchaseSerialLottoNumber(manualTicketMoney, autoTicketMoney);

		WinningLotto winningLotto = createWinningLottoNumbers();

		produceLottoResult(lottoMoney, purchasedLottos, winningLotto);
	}

	private static LottoMoney createAutoTicketMoney(
			LottoMoney lottoMoney, LottoMoney manualTicketMoney) {

		LottoMoney autoTicketMoney;
		do {
			autoTicketMoney = createAutoTicketMoneyIfValid(
					lottoMoney, manualTicketMoney);
		} while (autoTicketMoney.isInvalidInstance());
		return autoTicketMoney;
	}

	private static LottoMoney createAutoTicketMoneyIfValid(
			LottoMoney lottoMoney, LottoMoney manualTicketMoney) {

		try {
			return lottoMoney.subtract(manualTicketMoney);
		} catch (PurchaseManualLottoIllegalArgumentException e) {
			return LottoMoney.invalidInstance();
		}
	}

	private static PurchasedLottos purchaseSerialLottoNumber(
			LottoMoney manualTicketMoney, LottoMoney autoTicketMoney) {

		PurchasedLottos manualTickets = purchaseManualLotto(manualTicketMoney);
		PurchasedLottos autoSerialLottoNumber = purchaseAutoLotto(autoTicketMoney);
		OutputView.printPurchasedSerialLottoNumber(manualTickets, autoSerialLottoNumber);
		return manualTickets.addAll(autoSerialLottoNumber);
	}

	private static LottoMoney createManualTicketMoney(
			LottoMoney lottoMoney) {

		LottoMoney manualTicketMoney;
		do {
			manualTicketMoney = createManualTicketMoneyIfValid(lottoMoney);
		} while (manualTicketMoney.isInvalidInstance());
		return manualTicketMoney;
	}

	private static LottoMoney createManualTicketMoneyIfValid(
			LottoMoney lottoMoney) {

		int manualTicketNumber = InputView.inputManualTicketNumber();

		if (lottoMoney.canNotPurchase(manualTicketNumber)) {
			OutputView.printWhenManualMoneyIsMoreThanTotalMoney();
			return LottoMoney.invalidInstance();
		}

		try {
			return LottoMoney.ofLottoCount(manualTicketNumber);
		} catch (PurchaseManualLottoIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return LottoMoney.invalidInstance();
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

	private static PurchasedLottos purchaseManualLotto(
			LottoMoney manualLottoMoney) {
		OutputView.printInputManualLottoNumbersMessage();

		PurchasedLottos purchasedLottos = PurchasedLottos.empty();
		for (int i = 0; i < manualLottoMoney.countPurchasedTickets(); i++) {
			addOnePurchasedManualLottos(purchasedLottos);
		}

		return purchasedLottos;
	}

	private static void addOnePurchasedManualLottos(
			PurchasedLottos purchasedLottos) {
		boolean isSucceeded;
		do {
			isSucceeded = addOnePurchasedManualLottosIfValid(purchasedLottos);
		} while(!isSucceeded);
	}

	private static boolean addOnePurchasedManualLottosIfValid(
			PurchasedLottos purchasedLottos) {
		try {
			purchasedLottos.add(prepareManualLottoNumber());
			return true;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private static String prepareManualLottoNumber() {
		return InputView.inputManualLottoNumbers();
	}

	private static PurchasedLottos purchaseAutoLotto(
			LottoMoney lottoMoney) {

		return PurchasedLottos.of(lottoMoney,
				new AutoLottoFactory());
	}

	private static LottoMoney prepareLotto() {
		LottoMoney lottoMoney = createPurchaseMoney();
		OutputView.printCountOfPurchasedSerialLottoNumber(lottoMoney);
		return lottoMoney;
	}

	private static WinningLotto createWinningLottoNumbers() {
		WinningLotto winningLotto;
		do {
			winningLotto = createWinningLottoNumbersIfValid();
		} while (winningLotto.isInvalidInstance());

		return winningLotto;
	}

	private static WinningLotto createWinningLottoNumbersIfValid() {
		try {
			return WinningLotto.of(createWinningNumber(), createBonusNumber());
		} catch (WinningLottoIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return WinningLotto.invalidInstance();
		}
	}

	private static LottoMoney createPurchaseMoney() {
		LottoMoney lottoMoney;
		do {
			lottoMoney = createPurchaseMoneyIfValid();
		} while (lottoMoney.isInvalidInstance());

		return lottoMoney;
	}

	private static LottoMoney createPurchaseMoneyIfValid() {
		try {
			return LottoMoney.of(InputView.inputPurchaseMoney());
		} catch (LottoMoneyIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return LottoMoney.invalidInstance();
		}
	}

	private static Lotto createWinningNumber() {
		Lotto winningNumber;
		do {
			winningNumber = createWinningNumbersIfValid();
		} while (winningNumber.isInvalidInstance());

		return winningNumber;
	}

	private static Lotto createWinningNumbersIfValid() {
		try {
			return Lotto.of(InputView.inputWinningNumbers());
		} catch (IllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return Lotto.invalidInstance();
		}
	}

	private static LottoNumber createBonusNumber() {
		LottoNumber bonusNumber;
		do {
			bonusNumber = createBonusNumberIfValid();
		} while (bonusNumber.isInvalidInstance());

		return bonusNumber;
	}

	private static LottoNumber createBonusNumberIfValid() {
		try {
			return LottoNumber.of(InputView.inputBonusNumber());
		} catch (LottoNumberIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return LottoNumber.invalidInstance();
		}
	}
}
