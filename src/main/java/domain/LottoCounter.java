package domain;

public class LottoCounter {
	private static final String ERROR_NOT_DIVISIBLE_BY_UNIT_PRICE = "[ERROR] 금액은 1000원 단위로 나누어 떨어져야 합니다.";
	private static final String ERROR_NOT_POSITIVE = "[ERROR] 금액은 0원일 수 없습니다.";
	private static final String ERROR_NO_MORE_THAN_TOTAL_SIZE = "[ERROR] 전체 로또 갯수보다 수동 로또 갯수가 많을 수 없습니다.";
	private static final int UNIT_PRICE = 1000;
	private final int totalSize;
	private final int manualSize;

	public LottoCounter(int price, int manualSize) {
		validateDivisibleByThousand(price);
		validatePositive(price);
		validateLessThanTotalSize(price, manualSize);
		this.totalSize = price / UNIT_PRICE;
		this.manualSize = manualSize;
	}

	public int getManualSize() {
		return this.manualSize;
	}

	public int getTotalSize() {
		return this.totalSize;
	}

	private void validateDivisibleByThousand(int price) {
		if (price % UNIT_PRICE != 0) {
			throw new IllegalArgumentException(ERROR_NOT_DIVISIBLE_BY_UNIT_PRICE);
		}
	}

	private void validatePositive(int price) {
		if (price == 0) {
			throw new IllegalArgumentException(ERROR_NOT_POSITIVE);
		}
	}

	private void validateLessThanTotalSize(int price, int manualSize) {
		if (price / UNIT_PRICE < manualSize) {
			throw new IllegalArgumentException(ERROR_NO_MORE_THAN_TOTAL_SIZE);
		}
	}
}
