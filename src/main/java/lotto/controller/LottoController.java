package lotto.controller;

import lotto.domain.*;
import lotto.domain.LottoTicketFactory.RandomLottoTicketFactory;
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

		PurchasedLottoTickets purchasedLottoTickets
				= purchaseLottoTickets(manualTicketMoney, autoTicketMoney);

		WinningLottoNumbers winningLottoNumbers = createWinningLottoNumbers();

		produceLottoResult(purchaseMoney, purchasedLottoTickets, winningLottoNumbers);
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

	private static PurchasedLottoTickets purchaseLottoTickets(
			PurchaseMoney manualTicketMoney, PurchaseMoney autoTicketMoney) {

		PurchasedLottoTickets manualTickets = purchaseManualLotto(manualTicketMoney);
		PurchasedLottoTickets autoLottoTickets = purchaseAutoLotto(autoTicketMoney);
		OutputView.printPurchasedLottoTickets(manualTickets, autoLottoTickets);
		return manualTickets.addAll(autoLottoTickets);
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

		if (purchaseMoney.isLessThan(manualTicketNumber)) {
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
			PurchasedLottoTickets purchasedLottoTickets,
			WinningLottoNumbers winningLottoNumbers) {
		LottoResult lottoResult = LottoResult.of(purchasedLottoTickets, winningLottoNumbers);
		OutputView.printLottoResult(lottoResult);
		OutputView.printEarningRate(lottoResult.calculateEarningPercentage(purchaseMoney));
	}

	private static PurchasedLottoTickets purchaseManualLotto(
			PurchaseMoney manualTicketMoney) {

		List<SerialLottoNumber> serialLottoNumbers = new ArrayList<>();
		OutputView.printInputManualLottoNumbersMessage();
		for (int i = 0; i < manualTicketMoney.countPurchasedTickets(); i++) {
			SerialLottoNumber serialLottoNumber = prepareManualLottoTicket();
			serialLottoNumbers.add(serialLottoNumber);
		}

		return new PurchasedLottoTickets(serialLottoNumbers);
	}

	private static SerialLottoNumber prepareManualLottoTicket() {
		SerialLottoNumber serialLottoNumber;
		do {
			String input = InputView.inputManualLottoNumbers();
			serialLottoNumber = prepareManualLottoTicketIfValid(input);
		} while (serialLottoNumber == null);
		return serialLottoNumber;
	}

	private static SerialLottoNumber prepareManualLottoTicketIfValid(String input) {
		try {
			return SerialLottoNumber.of(input);
		} catch (LottoTicketIllegalArgumentException | LottoNumberIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static PurchasedLottoTickets purchaseAutoLotto(
			PurchaseMoney purchaseMoney) {

		return PurchasedLottoTickets.of(purchaseMoney,
				new RandomLottoTicketFactory());
	}

	private static PurchaseMoney prepareLotto() {
		PurchaseMoney purchaseMoney = createPurchaseMoney();
		OutputView.printPurchasedLottoTicketsCount(purchaseMoney);
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
