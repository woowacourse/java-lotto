package lotto.domain;

import java.util.Objects;

/**
 * 구입 금액과 수동 구입 개수를 검증하기 위한 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/29
 */
public class PurchaseInformation {
	private static final String PURCHASE_MONEY_IS_NULL_MESSAGE = "구입 금액이 존재하지 않습니다.";
	private static final String LACK_OF_PURCHASE_MONEY_MESSAGE = "구입 금액이 충분하지 않습니다.";
	private static final String INVALID_MANUAL_COUNT_MESSAGE = "수동 구입 개수로 음수를 입력할 수 없습니다.";

	private final PurchaseMoney purchaseMoney;
	private final int manualCount;

	public PurchaseInformation(PurchaseMoney purchaseMoney, int manualCount) {
		validate(purchaseMoney, manualCount);
		this.purchaseMoney = purchaseMoney;
		this.manualCount = manualCount;
	}

	private void validate(PurchaseMoney purchaseMoney, int manualCount) {
		validatePurchaseMoneyIsNotNull(purchaseMoney);
		validateManualCount(manualCount);
		validateMoneyAmount(purchaseMoney, manualCount);
	}

	private void validateManualCount(int manualCount) {
		if (manualCount < 0) {
			throw new IllegalArgumentException(INVALID_MANUAL_COUNT_MESSAGE);
		}
	}

	private void validateMoneyAmount(PurchaseMoney purchaseMoney, int manualCount) {
		long manualPurchaseMoney = manualCount * LottoGeneratable.LOTTO_PRICE;
		if (purchaseMoney.canNotPayable(manualPurchaseMoney)) {
			throw new IllegalArgumentException(LACK_OF_PURCHASE_MONEY_MESSAGE);
		}
	}

	private void validatePurchaseMoneyIsNotNull(PurchaseMoney purchaseMoney) {
		if (Objects.isNull(purchaseMoney)) {
			throw new IllegalArgumentException(PURCHASE_MONEY_IS_NULL_MESSAGE);
		}
	}

	public PurchaseMoney getPurchaseMoney() {
		return purchaseMoney;
	}

	public int getManualCount() {
		return manualCount;
	}
}
