package lotto.controller;

import lotto.domain.*;
import lotto.domain.PurchasedLottoTicketsFactory;
import lotto.domain.LottoTicketFactory.RandomLottoTicketFactory;
import lotto.exceptions.LottoTicketIllegalArgumentException;
import lotto.exceptions.PurchaseMoneyIllegalArgumentException;
import lotto.exceptions.WinningLottoNumbersIllegalArgumentException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		PurchaseMoney purchaseMoney = prepareLotto();

		PurchasedLottoTickets purchasedLottoTickets = purchaseLotto(purchaseMoney);

		WinningLottoNumbers winningLottoNumbers = createWinningLottoNumbers();

		produceLottoResult(purchaseMoney, purchasedLottoTickets, winningLottoNumbers);
	}

	private static void produceLottoResult(
			PurchaseMoney purchaseMoney,
			PurchasedLottoTickets purchasedLottoTickets,
			WinningLottoNumbers winningLottoNumbers) {
		LottoResult lottoResult = LottoResultFactory.create(purchasedLottoTickets, winningLottoNumbers);
		OutputView.printLottoResult(lottoResult);
		OutputView.printEarningRate(lottoResult.calculateEarningRate(purchaseMoney));
	}

	private static PurchasedLottoTickets purchaseLotto(
			PurchaseMoney purchaseMoney) {

		PurchasedLottoTickets purchasedLottoTickets
				= PurchasedLottoTicketsFactory.create(purchaseMoney,
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
		WinningLottoNumbers winningLottoNumbers = createWinningLottoNumbersIfValid();
		while (winningLottoNumbers == null) {
			winningLottoNumbers = createWinningLottoNumbersIfValid();

		}
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
		PurchaseMoney purchaseMoney = createPurchaseMoneyIfValid();
		while (purchaseMoney == null) {
			purchaseMoney = createPurchaseMoneyIfValid();
		}
		return createPurchaseMoneyIfValid();
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
		SerialLottoNumber winningNumber = createWinningNumbersIfValid();
		while (winningNumber == null) {
			winningNumber = createWinningNumbersIfValid();
		}
		return winningNumber;
	}

	private static SerialLottoNumber createWinningNumbersIfValid() {
		try {
			return SerialLottoNumberFactory.create(InputView.inputWinningNumbers());
		} catch (IllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}

	private static LottoNumber createBonusNumber() {
		LottoNumber bonusNumber = createBonusNumberIfValid();
		while (bonusNumber == null) {
			bonusNumber = createBonusNumberIfValid();
		}
		return bonusNumber;
	}

	private static LottoNumber createBonusNumberIfValid() {
		try {
			return new LottoNumber(InputView.inputBonusNumber());
		} catch (LottoTicketIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return null;
		}
	}
}
