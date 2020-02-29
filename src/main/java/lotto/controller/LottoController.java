package lotto.controller;

import lotto.domain.money.Money;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;
import lotto.domain.random.RandomLottoNumberGenerator;
import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoTickets;
import lotto.domain.result.LottoTicketsFactory;
import lotto.domain.result.Winning;
import lotto.exceptions.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		Money purchaseMoney = validPurchaseMoney();

		LottoTickets lottoTickets = createLottoTickets(purchaseMoney);

		Winning winning = validWinning();

		LottoResult lottoResult = LottoResult.of(winning, lottoTickets);
		OutputView.printLottoResult(lottoResult);
		OutputView.printProfitRate(lottoResult.calculateProfitRate(purchaseMoney));
	}

	private static Money validPurchaseMoney() {
		try {
			return new Money(InputView.inputPurchaseMoney());
		} catch (MoneyIllegalException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return validPurchaseMoney();
		}
	}

	private static LottoTickets createLottoTickets(Money purchaseMoney) {
		int manualTicketCount = validManualTicketCount(purchaseMoney);
		int autoTicketCount = purchaseMoney.totalTicketCount() - manualTicketCount;

		LottoTickets manualLottoTickets = validManualLottoTicket(manualTicketCount);
		LottoTickets autoLottoTickets = LottoTicketsFactory.of(autoTicketCount, new RandomLottoNumberGenerator());

		LottoTickets lottoTickets = LottoTickets.merge(manualLottoTickets, autoLottoTickets);

		OutputView.printLottoTicketsCount(manualTicketCount, autoTicketCount);
		OutputView.printLottoTickets(lottoTickets);

		return lottoTickets;
	}

	private static int validManualTicketCount(Money purchaseMoney) {
		try {
			int manualTicketCount = InputView.inputManualTicketCount();
			purchaseMoney.checkCanBuy(manualTicketCount);
			return manualTicketCount;
		} catch (TicketCountIllegalException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return validManualTicketCount(purchaseMoney);
		}
	}

	private static LottoTickets validManualLottoTicket(int manualTicketCount) {
		try {
			return LottoTicketsFactory.of(InputView.inputManualLottoTicket(manualTicketCount));
		} catch (NumberFormatException | NotSixSizeException | LottoNumberIllegalException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return validManualLottoTicket(manualTicketCount);
		}
	}

	private static Winning validWinning() {
		try {
			return new Winning(validWinningNumbers(), validBonusNumber());
		} catch (DuplicateWinningNumberException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return validWinning();
		}
	}

	private static SerialLottoNumber validWinningNumbers() {
		try {
			return SerialLottoNumberFactory.of(InputView.inputWinningNumbers());
		} catch (NumberFormatException | NotSixSizeException | LottoNumberIllegalException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return validWinningNumbers();
		}
	}

	private static LottoNumber validBonusNumber() {
		try {
			return LottoNumber.of(InputView.inputBonusNumber());
		} catch (LottoNumberIllegalException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return validBonusNumber();
		}
	}
}
