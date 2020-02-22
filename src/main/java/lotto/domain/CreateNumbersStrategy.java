package lotto.domain;

public interface CreateNumbersStrategy {
	LottoTickets create(PurchasingAmount purchasingAmount);
}
