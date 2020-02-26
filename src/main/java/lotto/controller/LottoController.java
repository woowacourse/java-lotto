package lotto.controller;

import lotto.domain.*;
import lotto.exceptions.LottoTicketIllegalArgumentException;
import lotto.exceptions.PurchaseManualTicketIllegalArgumentException;
import lotto.exceptions.PurchaseMoneyIllegalArgumentException;
import lotto.exceptions.WinningLottoNumbersIllegalArgumentException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
	public static void run() {
		PurchaseMoney purchaseMoney = createPurchaseMoney();
		int manualTicketNumber = validManualTicketNumber(purchaseMoney);
		int autoTicketNumber = purchaseMoney.countPurchasedTickets() - manualTicketNumber;

		PurchasedLottoTickets purchasedLottoTickets = purchaseLottoTickets(manualTicketNumber, autoTicketNumber);

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

	private static PurchasedLottoTickets purchaseLottoTickets(int manualTicketNumber, int autoTicketNumber) {
		PurchasedLottoTickets purchasedLottoTickets
				= createLottoTickets(manualTicketNumber, autoTicketNumber);

		OutputView.printPurchasedLottoTicketsCount(manualTicketNumber, autoTicketNumber);
		OutputView.printPurchasedLottoTickets(purchasedLottoTickets);
		return purchasedLottoTickets;
	}

	private static PurchasedLottoTickets createLottoTickets(int manualTicketNumber,
															int autoTicketNumber) {
		RandomLottoTicketFactory randomLottoTicketFactory =
				new RandomLottoTicketFactory(new RandomLottoNumbersGenerator());

		List<SerialLottoNumber> lottoTickets = new ArrayList<>();

		for (int i = 0; i < manualTicketNumber; i++) {
			lottoTickets.add(validManualTicket());
		}
		for (int i = 0; i < autoTicketNumber; i++) {
			lottoTickets.add(randomLottoTicketFactory.create());
		}
		return new PurchasedLottoTickets(lottoTickets);
	}

	private static SerialLottoNumber validManualTicket() {
		try {
			return SerialLottoNumberFactory.create(InputView.inputManualTicket());
		} catch (NumberFormatException | LottoTicketIllegalArgumentException e) {
			OutputView.printWarningMessage(e.getMessage());
			return validManualTicket();
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
			return SerialLottoNumberFactory.create(InputView.inputWinningNumbers());
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
