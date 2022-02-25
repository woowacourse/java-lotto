package domain;

public class Payment {
	private static final int LOTTO_PRICE = 1000;
	private static final int PAYMENT_LIMIT_PRICE = 100000;
	private final int payment;

	public Payment(int payment) {
		checkPayment(payment);
		this.payment = payment;
	}

	public Payment(String payment) {
		this.payment = toInt(payment);
		checkPayment(this.payment);
	}

	private void checkPayment(int payment) {
		checkNegative(payment);
		checkMinLottoPrice(payment);
		checkPaymentLimit(payment);
	}

	private int toInt(String payment) {
		int changeInt = Integer.parseInt(payment);
		return changeInt;
	}

	private void checkMinLottoPrice(int payment) {
		if (payment < LOTTO_PRICE) {
			throw new IllegalArgumentException("1000원 이상 입력해주세요!");
		}
	}

	private void checkNegative(int payment) {
		if (payment < 0) {
			throw new IllegalArgumentException("음수는 불가능합니다.");
		}
	}

	private void checkPaymentLimit(int payment) {
		if (payment > PAYMENT_LIMIT_PRICE) {
			throw new IllegalArgumentException("로또는 한사람 당 10만원씩만 살 수 있습니다.");
		}
	}

	public int calculateLottoCount() {
		return payment / LOTTO_PRICE;
	}

	public double calculateDivision(int divisionValue) {
		return (double)divisionValue / payment;
	}
}
