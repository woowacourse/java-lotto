package lotto.controller;

import lotto.domain.*;
import lotto.domain.SerialLottoNumberFactory.AutoLottoFactory;
import lotto.exceptions.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

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
		} while (autoTicketMoney == null);
		return autoTicketMoney;
	}

	private static LottoMoney createAutoTicketMoneyIfValid(
			LottoMoney lottoMoney, LottoMoney manualTicketMoney) {

		try {
			return lottoMoney.subtract(manualTicketMoney);
		} catch (PurchaseManualLottoIllegalArgumentException e) {
			return null;
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
		} while (manualTicketMoney == null);
		return manualTicketMoney;
	}

	private static LottoMoney createManualTicketMoneyIfValid(
			LottoMoney lottoMoney) {

		int manualTicketNumber = InputView.inputManualTicketNumber();

		if (lottoMoney.canNotPurchase(manualTicketNumber)) {
			OutputView.printWhenManualMoneyIsMoreThanTotalMoney();
			return null;
		}

		try {
			return LottoMoney.of(manualTicketNumber);
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

	private static PurchasedLottos purchaseManualLotto(
			LottoMoney manualTicketMoney) {

		List<Lotto> serialLottoNumbers = new ArrayList<>();
		OutputView.printInputManualLottoNumbersMessage();
		for (int i = 0; i < manualTicketMoney.countPurchasedTickets(); i++) {
			serialLottoNumbers.add(prepareManualSerialLottoNumber());
		}

		return new PurchasedLottos(serialLottoNumbers);
	}

	private static Lotto prepareManualSerialLottoNumber() {
		Lotto serialLottoNumber;
		do {
			String input = InputView.inputManualLottoNumbers();
			serialLottoNumber = prepareManualSerialLottoNumberIfValid(input);
		} while (serialLottoNumber == null);
		return serialLottoNumber;
	}

	private static Lotto prepareManualSerialLottoNumberIfValid(String input) {
		try {
			return Lotto.of(input);
		} catch (LottoIllegalArgumentException | LottoNumberIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
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
		} while (winningLotto == null);

		return winningLotto;
	}

	private static WinningLotto createWinningLottoNumbersIfValid() {
		try {
			return new WinningLotto(createWinningNumber(), createBonusNumber());
		} catch (WinningLottoIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static LottoMoney createPurchaseMoney() {
		LottoMoney lottoMoney;
		do {
			lottoMoney = createPurchaseMoneyIfValid();
		} while (lottoMoney == null);

		return lottoMoney;
	}

	private static LottoMoney createPurchaseMoneyIfValid() {
		try {
			return new LottoMoney(InputView.inputPurchaseMoney());
		} catch (LottoMoneyIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static Lotto createWinningNumber() {
		Lotto winningNumber;
		do {
			winningNumber = createWinningNumbersIfValid();
		} while (winningNumber == null);

		return winningNumber;
	}

	private static Lotto createWinningNumbersIfValid() {
		try {
			return Lotto.of(InputView.inputWinningNumbers());
		} catch (IllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static LottoNumber createBonusNumber() {
		LottoNumber bonusNumber;
		do {
			bonusNumber = createBonusNumberIfValid();
		} while (bonusNumber == null);

		return bonusNumber;
	}

	private static LottoNumber createBonusNumberIfValid() {
		try {
			return LottoNumber.of(InputView.inputBonusNumber());
		} catch (LottoNumberIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}
}
