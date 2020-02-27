package lotto.domain;

/**
 * 로또 구입 금액 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoPurchaseMoney {
	private static final long MONEY_UNIT = 1_000;
	private static final String INVALID_PURCHASE_MONEY_MESSAGE = "잘못된 구입 금액을 입력하셨습니다.";
	private static final String CAN_NOT_PAYABLE_MESSAGE = "지불할 수 없는 금액입니다.";

	private long lottoMoney;

	public LottoPurchaseMoney(long lottoMoney) {
		validate(lottoMoney);
		this.lottoMoney = lottoMoney;
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

	public boolean canPayable(long amount) {
		return lottoMoney - amount >= 0;
	}

	public void pay(long amount) {
		if (!canPayable(amount)) {
			throw new IllegalArgumentException(CAN_NOT_PAYABLE_MESSAGE);
		}
		lottoMoney -= amount;
	}

	public long multiply(long count) {
		return lottoMoney * count;
	}

	public long get() {
		return lottoMoney;
	}

	@Override
	public String toString() {
		return String.valueOf(lottoMoney);
	}
}
