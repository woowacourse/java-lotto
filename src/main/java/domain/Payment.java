package domain;

public class Payment {
	private static final int LOTTO_PRICE = 1000;
	private static final int PAYMENT_LIMIT_PRICE = 100000;
	private int payment;

	public Payment(String payment) {
		this.payment = toInt(payment);
	}

	//Todo: 팩토리 메서드 공부 한 후, 적용에 대해 의견 나누기
	// public static Payment create(String payment) {
	// 	return new Payment(Integer.parseInt(payment));
	// }

	private int toInt(String payment) {
		int changeInt = Integer.parseInt(payment);
		checkDivideByLottoPrice(changeInt);
		checkNegative(changeInt);
		checkPaymentLimit(changeInt);
		return changeInt;
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
