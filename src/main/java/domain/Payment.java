package domain;

public class Payment {
	private static final int LOTTO_PRICE = 1000;
	private static final int PAYMENT_LIMIT_PRICE = 100000;
	private int payment;

	public Payment(String payment) {
		this.payment = toInt(payment);
	}

	private int toInt(String payment) {
		int changeInt = Integer.parseInt(payment);
		checkDivideByLottoPrice(changeInt);
		checkNegative(changeInt);
		checkPaymentLimit(changeInt);
		return changeInt;
	}

	public int calculateLottoCount() {
		return payment / LOTTO_PRICE;
	}

	public double calculateProfitRate(int totalProfit) {
		return (double)totalProfit / payment;
	}

	private void checkPaymentLimit(int payment) {
		if (payment > PAYMENT_LIMIT_PRICE) {
			throw new IllegalArgumentException("로또는 한사람 당 10만원씩만 살 수 있습니다. ");
		}
	}

	private void checkDivideByLottoPrice(int payment) {
		if (payment % LOTTO_PRICE != 0) {
			throw new IllegalArgumentException("1000원 단위로 입력해주세요!");
		}
	}

	private void checkNegative(int payment) {
		if (payment < 0) {
			throw new IllegalArgumentException("음수는 불가능합니다.");
		}
	}

	public int getPayment() {
		return payment;
	}
}
