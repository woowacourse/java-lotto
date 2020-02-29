package lotto.domain;

import lotto.exception.LottoCountException;

public class LottoCount {
	private static final int MONEY_UNIT = 1000;
	private static final int MINIMUM_MONEY = 1000;
	public static final String NOT_ENOUGH_MONEY = "돈이 부족합니다.";
	public static final String MONEY_UNIT_ERROR = "천 원 단위로 입력하셔야 합니다.";
	public static final String MANUAL_AMOUNT_ERROR = "수동 구입 장수가 올바르지 않습니다.";


	private final int lottoCount;
	private final int manualLottoCount;

	public LottoCount(int money, int manualLottoCount) {
		validate(money, manualLottoCount);
		this.lottoCount = money / MONEY_UNIT;
		this.manualLottoCount = manualLottoCount;
	}

	private void validate(int money, int manualLottoCount) {
		validateLackOf(money);
		validateMoneyUnit(money);
		validateManualAmount(money, manualLottoCount);
	}

	private void validateLackOf(int money) {
		if (money < MINIMUM_MONEY) {
			throw new LottoCountException(NOT_ENOUGH_MONEY);
		}
	}

	private void validateMoneyUnit(int money) {
		if (money % MONEY_UNIT != 0) {
			throw new LottoCountException(MONEY_UNIT_ERROR);
		}
	}

	private void validateManualAmount(int money, int manualLottoCount) {
		if (manualLottoCount > (money / 1000) || manualLottoCount < 0) {
			throw new LottoCountException(MANUAL_AMOUNT_ERROR);
		}
	}

	public int getManualLottoCount() {
		return manualLottoCount;
	}

	public int getAutoLottoCount() {
		return lottoCount - manualLottoCount;
	}

	public int getTotalLottoCount() {
		return lottoCount;
	}
}
