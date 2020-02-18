package lotto.domain;

public class LottoMoney {
	private static final int LOTTO_PURCHASE_UNIT = 1000;
	private final int lottoMoney;

	public LottoMoney(int lottoMoney) {
		validate(lottoMoney);
		this.lottoMoney = lottoMoney;
	}

	private void validate(int lottoMoney) {
		if (isUnitFit(lottoMoney)) {
			throw new IllegalArgumentException("잘못된 구입 금액을 입력하셨습니다.");
		}
	}

	private boolean isUnitFit(int lottoMoney) {
		return lottoMoney % LOTTO_PURCHASE_UNIT != 0;
	}

	public int calculateBuyCount() {
		return lottoMoney / LOTTO_PURCHASE_UNIT;
	}
}
