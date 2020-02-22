package lotto.domain;

public class LottoTicketFactory {
	public static LottoTickets create(PurchasingAmount purchasingAmount, CreateNumbersStrategy createNumbersStrategy) {
		return createNumbersStrategy.create(purchasingAmount);
	}
}
