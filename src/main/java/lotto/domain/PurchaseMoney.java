package lotto.domain;

/**
 * 로또 구입 금액 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class PurchaseMoney {
	private static final long MONEY_UNIT = 1_000;
	private static final String INVALID_PURCHASE_MONEY_MESSAGE = "잘못된 구입 금액을 입력하셨습니다.";
	private static final String CAN_NOT_PAYABLE_MESSAGE = "지불할 수 없는 금액입니다.";

	private long money;

	public PurchaseMoney(long money) {
		validate(money);
		this.money = money;
	}

	private void validate(long lottoMoney) {
		if (canNotDivideByUnit(lottoMoney) || isNegative(lottoMoney)) {
			throw new IllegalArgumentException(INVALID_PURCHASE_MONEY_MESSAGE);
		}
	}

	private boolean canNotDivideByUnit(long lottoMoney) {
		return lottoMoney % MONEY_UNIT != 0;
	}

	private boolean isNegative(long lottoMoney) {
		return lottoMoney < 0;
	}

	public boolean canPayable(PurchaseMoney payingMoney) {
		return money >= payingMoney.money;
	}

	public boolean canNotPayable(PurchaseMoney payingMoney) {
		return money < payingMoney.money;
	}

	public void pay(PurchaseMoney payingMoney) {
		if (canNotPayable(payingMoney)) {
			throw new IllegalArgumentException(CAN_NOT_PAYABLE_MESSAGE);
		}
		money -= payingMoney.money;
	}

	public long multiply(long number) {
		return money * number;
	}

	public long divide(PurchaseMoney purchaseMoney) {
		return money / purchaseMoney.money;
	}

	public long get() {
		return money;
	}

	@Override
	public String toString() {
		return String.valueOf(money);
	}
}
