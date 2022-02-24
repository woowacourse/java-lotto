package domain;

public class Payment {
	private static final int LOTTO_PRICE = 1000;
	private static final int PAYMENT_LIMIT_PRICE = 100000;
	private int payment;

	public Payment(String payment) {
		this.payment = toInt(payment);
	}

	private int toInt(String input) {
		int payment = Integer.parseInt(input);
		checkGreaterThanLottoPrice(payment);
		checkPaymentLimit(payment);
		return payment;
	}

	private void checkGreaterThanLottoPrice(int payment) {
		if (payment < LOTTO_PRICE) {
			throw new IllegalArgumentException("금액이 로또 가격보다 작습니다.");
		}
	}

	private void checkPaymentLimit(int payment) {
		if (payment > PAYMENT_LIMIT_PRICE) {
			throw new IllegalArgumentException("로또는 한사람 당 10만원씩만 살 수 있습니다. ");
		}
	}

	public int calculateLottoCount() {
		return payment / LOTTO_PRICE;
	}

	public double calculateProfitRate(int totalProfit) {
		return (double)totalProfit / payment;
	}
}
