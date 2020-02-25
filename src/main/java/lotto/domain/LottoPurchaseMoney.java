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

	private final long lottoMoney;

	public LottoPurchaseMoney(long lottoMoney) {
		validate(lottoMoney);
		this.lottoMoney = lottoMoney;
	}

	private void validate(long lottoMoney) {
		if (isUnitFit(lottoMoney)) {
			throw new IllegalArgumentException(INVALID_PURCHASE_MONEY_MESSAGE);
		}
	}

	private boolean isUnitFit(long lottoMoney) {
		return lottoMoney % MONEY_UNIT != 0;
	}

	public long get() {
		return lottoMoney;
	}

	public long calculateBuyCount() {
		return lottoMoney / MONEY_UNIT;
	}

	public long multiply(long count) {
		return lottoMoney * count;
	}

	@Override
	public String toString() {
		return String.valueOf(lottoMoney);
	}
}
