package lotto.domain.purchase;

public class ManualLottoTicketCount {
	private int manualLottoTicketCount;

	public ManualLottoTicketCount(String manualLottoTicketCount, PurchasingCount purchasingCount) {
		int parsedManualLottoTicketNumber = parseToInt(manualLottoTicketCount);
		validate(parsedManualLottoTicketNumber, purchasingCount);
		this.manualLottoTicketCount = parsedManualLottoTicketNumber;
	}

	private static int parseToInt(String manualLottoTicketNumber) {
		try {
			return Integer.parseInt(manualLottoTicketNumber);
		} catch (NumberFormatException e) {
			throw new InvalidManualLottoTicketCountException(InvalidManualLottoTicketCountException.NOT_INTEGER);
		}
	}

	private void validate(int manualLottoTicketNumber, PurchasingCount purchasingCount) {
		validateNegative(manualLottoTicketNumber);
		validatePurchasable(manualLottoTicketNumber, purchasingCount);
	}

	private void validateNegative(int manualLottoTicketNumber) {
		if (manualLottoTicketNumber < 0) {
			throw new InvalidManualLottoTicketCountException(InvalidManualLottoTicketCountException.NEGATIVE);
		}
	}

	private void validatePurchasable(int manualLottoTicketNumber, PurchasingCount purchasingCount) {
		if (purchasingCount.isOverBy(manualLottoTicketNumber)) {
			throw new InvalidManualLottoTicketCountException(InvalidManualLottoTicketCountException.NOT_PURCHASABLE);
		}
	}

	public int getManualLottoTicketCount() {
		return manualLottoTicketCount;
	}
}
