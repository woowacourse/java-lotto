package domain;

public class OrderForm {
	private final Payment payment;
	private final int quantity;

	public OrderForm(Payment payment, int quantity) {
		checkNegative(quantity);
		checkRange(payment, quantity);
		this.payment = payment;
		this.quantity = quantity;
	}

	private void checkNegative(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("음수는 불가능합니다.");
		}
	}

	private void checkRange(Payment payment, int quantity) {
		if (!payment.canBuy(quantity)) {
			throw new IllegalArgumentException("구매 가능한 갯수를 초과했습니다.");
		}
	}

	public int calculateAutoLottoCount() {
		return payment.calculateLottoCount() - quantity;
	}

	public boolean isEqualQuantity(Lottos manualLottos) {
		return manualLottos.getSize() == quantity;
	}
}
