package lotto.controller;

import lotto.domain.*;
import lotto.domain.SerialLottoNumberFactory.AutoSerialLottoNumberFactory;
import lotto.exceptions.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
	public static void run() {
		PurchaseMoney purchaseMoney = prepareLotto();

		PurchaseMoney manualTicketMoney = createManualTicketMoney(purchaseMoney);
		PurchaseMoney autoTicketMoney
				= createAutoTicketMoney(purchaseMoney, manualTicketMoney);

		PurchasedSerialLottoNumbers purchasedSerialLottoNumbers
				= purchaseSerialLottoNumber(manualTicketMoney, autoTicketMoney);

		WinningLottoNumbers winningLottoNumbers = createWinningLottoNumbers();

		produceLottoResult(purchaseMoney, purchasedSerialLottoNumbers, winningLottoNumbers);
	}

	private static PurchaseMoney createAutoTicketMoney(
			PurchaseMoney purchaseMoney, PurchaseMoney manualTicketMoney) {

		PurchaseMoney autoTicketMoney;
		do {
			autoTicketMoney = createAutoTicketMoneyIfValid(
					purchaseMoney, manualTicketMoney);
		} while (autoTicketMoney == null);
		return autoTicketMoney;
	}

	private static PurchaseMoney createAutoTicketMoneyIfValid(
			PurchaseMoney purchaseMoney, PurchaseMoney manualTicketMoney) {

		try {
			return purchaseMoney.subtract(manualTicketMoney);
		} catch (PurchaseManualTicketIllegalArgumentException e) {
			return null;
		}
	}

	private static PurchasedSerialLottoNumbers purchaseSerialLottoNumber(
			PurchaseMoney manualTicketMoney, PurchaseMoney autoTicketMoney) {

		PurchasedSerialLottoNumbers manualTickets = purchaseManualLotto(manualTicketMoney);
		PurchasedSerialLottoNumbers autoSerialLottoNumber = purchaseAutoLotto(autoTicketMoney);
		OutputView.printPurchasedSerialLottoNumber(manualTickets, autoSerialLottoNumber);
		return manualTickets.addAll(autoSerialLottoNumber);
	}

	private static PurchaseMoney createManualTicketMoney(
			PurchaseMoney purchaseMoney) {

		PurchaseMoney manualTicketMoney;
		do {
			manualTicketMoney = createManualTicketMoneyIfValid(purchaseMoney);
		} while (manualTicketMoney == null);
		return manualTicketMoney;
	}

	private static PurchaseMoney createManualTicketMoneyIfValid(
			PurchaseMoney purchaseMoney) {

		int manualTicketNumber = InputView.inputManualTicketNumber();

		if (purchaseMoney.canNotPurchase(manualTicketNumber)) {
			OutputView.printWhenManualMoneyIsMoreThanTotalMoney();
			return null;
		}

		try {
			return PurchaseMoney.of(manualTicketNumber);
		} catch (PurchaseManualTicketIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static void produceLottoResult(
			PurchaseMoney purchaseMoney,
			PurchasedSerialLottoNumbers purchasedSerialLottoNumbers,
			WinningLottoNumbers winningLottoNumbers) {
		LottoResult lottoResult = LottoResult.of(purchasedSerialLottoNumbers, winningLottoNumbers);
		OutputView.printLottoResult(lottoResult);
		OutputView.printEarningRate(lottoResult.calculateEarningPercentage(purchaseMoney));
	}

	private static PurchasedSerialLottoNumbers purchaseManualLotto(
			PurchaseMoney manualTicketMoney) {

		List<SerialLottoNumber> serialLottoNumbers = new ArrayList<>();
		OutputView.printInputManualLottoNumbersMessage();
		for (int i = 0; i < manualTicketMoney.countPurchasedTickets(); i++) {
			serialLottoNumbers.add(prepareManualSerialLottoNumber());
		}

		return new PurchasedSerialLottoNumbers(serialLottoNumbers);
	}

	private static SerialLottoNumber prepareManualSerialLottoNumber() {
		SerialLottoNumber serialLottoNumber;
		do {
			String input = InputView.inputManualLottoNumbers();
			serialLottoNumber = prepareManualSerialLottoNumberIfValid(input);
		} while (serialLottoNumber == null);
		return serialLottoNumber;
	}

	private static SerialLottoNumber prepareManualSerialLottoNumberIfValid(String input) {
		try {
			return SerialLottoNumber.of(input);
		} catch (SerialLottoNumberIllegalArgumentException | LottoNumberIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static PurchasedSerialLottoNumbers purchaseAutoLotto(
			PurchaseMoney purchaseMoney) {

		return PurchasedSerialLottoNumbers.of(purchaseMoney,
				new AutoSerialLottoNumberFactory());
	}

	private static PurchaseMoney prepareLotto() {
		PurchaseMoney purchaseMoney = createPurchaseMoney();
		OutputView.printCountOfPurchasedSerialLottoNumber(purchaseMoney);
		return purchaseMoney;
	}

	private static WinningLottoNumbers createWinningLottoNumbers() {
		WinningLottoNumbers winningLottoNumbers;
		do {
			winningLottoNumbers = createWinningLottoNumbersIfValid();
		} while (winningLottoNumbers == null);

		return winningLottoNumbers;
	}

	private static WinningLottoNumbers createWinningLottoNumbersIfValid() {
		try {
			return new WinningLottoNumbers(createWinningNumber(), createBonusNumber());
		} catch (WinningLottoNumbersIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static PurchaseMoney createPurchaseMoney() {
		PurchaseMoney purchaseMoney;
		do {
			purchaseMoney = createPurchaseMoneyIfValid();
		} while (purchaseMoney == null);

		return purchaseMoney;
	}

	private static PurchaseMoney createPurchaseMoneyIfValid() {
		try {
			return new PurchaseMoney(InputView.inputPurchaseMoney());
		} catch (PurchaseMoneyIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static SerialLottoNumber createWinningNumber() {
		SerialLottoNumber winningNumber;
		do {
			winningNumber = createWinningNumbersIfValid();
		} while (winningNumber == null);

		return winningNumber;
	}

	private static SerialLottoNumber createWinningNumbersIfValid() {
		try {
			return SerialLottoNumber.of(InputView.inputWinningNumbers());
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
			return new LottoNumber(InputView.inputBonusNumber());
		} catch (LottoNumberIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}
}
