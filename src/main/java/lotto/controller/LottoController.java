package lotto.controller;

import lotto.domain.*;
import lotto.domain.LottoTicketFactory.RandomLottoTicketFactory;
import lotto.exceptions.LottoNumberIllegalArgumentException;
import lotto.exceptions.PurchaseMoneyIllegalArgumentException;
import lotto.exceptions.WinningLottoNumbersIllegalArgumentException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		PurchaseMoney purchaseMoney = prepareLotto();

		int manualTicketNumber = InputView.inputManualTicketNumber();
		PurchaseMoney manualTicketMoney = PurchaseMoney.of(manualTicketNumber);
		PurchaseMoney autoTicketMoney
				= purchaseMoney.subtract(manualTicketMoney);

		PurchasedLottoTickets manualTickets = purchaseLotto(autoTicketMoney);

		PurchasedLottoTickets purchasedLottoTickets = purchaseLotto(purchaseMoney);

		WinningLottoNumbers winningLottoNumbers = createWinningLottoNumbers();

		produceLottoResult(purchaseMoney, purchasedLottoTickets, winningLottoNumbers);
	}

	private static void produceLottoResult(
			PurchaseMoney purchaseMoney,
			PurchasedLottoTickets purchasedLottoTickets,
			WinningLottoNumbers winningLottoNumbers) {
		LottoResult lottoResult = LottoResult.of(purchasedLottoTickets, winningLottoNumbers);
		OutputView.printLottoResult(lottoResult);
		OutputView.printEarningRate(lottoResult.calculateEarningPercentage(purchaseMoney));
	}

	private static PurchasedLottoTickets purchaseLotto(
			PurchaseMoney purchaseMoney) {

		PurchasedLottoTickets purchasedLottoTickets
				= PurchasedLottoTickets.of(purchaseMoney,
				new RandomLottoTicketFactory());
		OutputView.printPurchasedLottoTickets(purchasedLottoTickets);
		return purchasedLottoTickets;
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
