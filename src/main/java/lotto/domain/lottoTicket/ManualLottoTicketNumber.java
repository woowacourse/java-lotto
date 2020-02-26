package lotto.domain.lottoTicket;

import lotto.domain.lottoMoney.LottoMoney;

public class ManualLottoTicketNumber {
	private final int manualLottoTicketNumber;

	public ManualLottoTicketNumber(String manualLottoTicketNumber, LottoMoney lottoMoney) {
		int parsedManualLottoTicketNumber = parseToInt(manualLottoTicketNumber);

		validate(parsedManualLottoTicketNumber, lottoMoney);
		this.manualLottoTicketNumber = parsedManualLottoTicketNumber;
	}

	private static int parseToInt(String manualLottoTicketNumber) {
		try {
			return Integer.parseInt(manualLottoTicketNumber);
		} catch (NumberFormatException e) {
			throw new InvalidManualLottoTicketException(InvalidManualLottoTicketException.NOT_INTEGER);
		}
	}

	private void validate(int manualLottoTicketNumber, LottoMoney lottoMoney) {
		validateNegative(manualLottoTicketNumber);
		validatePurchasable(manualLottoTicketNumber, lottoMoney);
	}

	private void validateNegative(int manualLottoTicketNumber) {
		if (manualLottoTicketNumber < 0) {
			throw new InvalidManualLottoTicketException(InvalidManualLottoTicketException.NEGATIVE);
		}
	}

	private void validatePurchasable(int manualLottoTicketNumber, LottoMoney lottoMoney) {
		long purchasableNumber = lottoMoney.calculatePurchasableLottoTicket();
		if (manualLottoTicketNumber > purchasableNumber) {
			throw new InvalidManualLottoTicketException(InvalidManualLottoTicketException.NOT_PURCHASABLE);
		}
	}
}
