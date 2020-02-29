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

import java.util.ArrayList;
import java.util.List;

public class LottoController {
	public static void run() {
		Money purchaseMoney = validPurchaseMoney();

		int manualTicketCount = validManualTicketCount(purchaseMoney);
		int autoTicketCount = purchaseMoney.totalTicketCount() - manualTicketCount;

		LottoTickets manualLottoTickets = validManualLottoTicket(manualTicketCount);
		// TODO: autoLottoTicket의 팩토리 메서드 구현과 합치는 메서드 구현
		LottoTickets lottoTickets = new LottoTickets(createAutoLottoTickets(autoTicketCount));
		OutputView.printLottoTicketsCount(manualTicketCount, autoTicketCount);
		OutputView.printLottoTickets(lottoTickets);

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

	private static List<SerialLottoNumber> createAutoLottoTickets(int autoTicketCount) {
		RandomLottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();
		List<SerialLottoNumber> autoLottoTickets = new ArrayList<>();

		for (int i = 0; i < autoTicketCount; i++) {
			autoLottoTickets.add(SerialLottoNumberFactory.of(randomLottoNumberGenerator));
		}
		return autoLottoTickets;
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
