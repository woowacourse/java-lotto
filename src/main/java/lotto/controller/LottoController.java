package lotto.controller;

import lotto.domain.*;
import lotto.exceptions.LottoTicketIllegalArgumentException;
import lotto.exceptions.PurchaseManualTicketIllegalArgumentException;
import lotto.exceptions.PurchaseMoneyIllegalArgumentException;
import lotto.exceptions.WinningLottoNumbersIllegalArgumentException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		PurchaseMoney purchaseMoney = createPurchaseMoney();
		OutputView.printPurchasedLottoTicketsCount(purchaseMoney);

		int manualTicketNumber = validManualTicketNumber(purchaseMoney);
		int autoTicketNumber = purchaseMoney.countPurchasedTickets() - manualTicketNumber;

		RandomLottoTicketFactory randomLottoTicketFactory =
				new RandomLottoTicketFactory(new RandomLottoNumbersGenerator());
		PurchasedLottoTickets purchasedLottoTickets
				= PurchasedLottoTicketsFactory.of(autoTicketNumber, randomLottoTicketFactory);
		OutputView.printPurchasedLottoTickets(purchasedLottoTickets);

		WinningInformation winningInformation = createWinningInformation();

		LottoResult lottoResult = LottoResult.of(purchasedLottoTickets, winningInformation);
		OutputView.printLottoResult(lottoResult);

		OutputView.printEarningRate(lottoResult.calculateEarningRate(purchaseMoney));
	}

	private static PurchaseMoney createPurchaseMoney() {
		try {
			return new PurchaseMoney(InputView.inputPurchaseMoney());
		} catch (PurchaseMoneyIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return createPurchaseMoney();
		}
	}

	private static int validManualTicketNumber(PurchaseMoney purchaseMoney) {
		try {
			int manualTicketNumber = InputView.inputManualTicketNumber();
			purchaseMoney.checkCanBuy(manualTicketNumber);
			return manualTicketNumber;
		} catch (PurchaseManualTicketIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return validManualTicketNumber(purchaseMoney);
		}
	}

	private static WinningInformation createWinningInformation() {
		try {
			return new WinningInformation(createWinningNumbers(), createBonusNumber());
		} catch (WinningLottoNumbersIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return createWinningInformation();
		}
	}

	private static SerialLottoNumber createWinningNumbers() {
		try {
			return WinningLottoNumbersFactory.createWinningLottoNumbers(InputView.inputWinningNumbers());
		} catch (NumberFormatException | LottoTicketIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return createWinningNumbers();
		}
	}

	private static LottoNumber createBonusNumber() {
		try {
			return LottoNumber.getLottoNumber(InputView.inputBonusNumber());
		} catch (LottoTicketIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return createBonusNumber();
		}
	}
}
