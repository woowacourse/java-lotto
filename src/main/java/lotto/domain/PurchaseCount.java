package lotto.domain;

/**
 * 구매한 로또의 수를 저장하는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/03/02
 */
public class PurchaseCount {
	private static final String INVALID_PURCHASE_COUNT_MESSAGE = "구입 개수로 음수를 입력할 수 없습니다.";
	private static final String LACK_OF_MONEY_MESSAGE = "금액이 부족합니다.";

	private final long count;

	public PurchaseCount(long count) {
		validatePurchaseCount(count);
		this.count = count;
	}

	private void validatePurchaseCount(long manualCount) {
		if (manualCount < 0) {
			throw new IllegalArgumentException(INVALID_PURCHASE_COUNT_MESSAGE);
		}
	}

	public PurchaseCount calculateRestPurchaseCount(PurchaseMoney purchaseMoney) {
		try {
			return new PurchaseCount(purchaseMoney.divide(LottoGenerative.LOTTO_PRICE) - count);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(LACK_OF_MONEY_MESSAGE);
		}
	}

	public long get() {
		return count;
	}
}
