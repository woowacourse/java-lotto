package lotto.domain;

import java.math.BigInteger;
import java.util.Arrays;

public enum WinningType {
	FIRST_PLACE(6, BonusType.NO_MATTER, new BigInteger(Integer.toString(2_000_000_000))),
	SECOND_PLACE(5, BonusType.TRUE, new BigInteger(Integer.toString(30_000_000))),
	THIRD_PLACE(5, BonusType.FALSE, new BigInteger(Integer.toString(1_500_000))),
	FOURTH_PLACE(4, BonusType.NO_MATTER, new BigInteger(Integer.toString(50_000))),
	FIFTH_PLACE(3, BonusType.NO_MATTER, new BigInteger(Integer.toString(5_000))),
	NONE(-1, BonusType.NO_MATTER, new BigInteger(Integer.toString(0)));

	private int sameNumberCount;
	private BonusType bonusType;
	private BigInteger winningAmount;

	WinningType(int sameNumberCount, BonusType bonusType, BigInteger winningAmount) {
		this.sameNumberCount = sameNumberCount;
		this.bonusType = bonusType;
		this.winningAmount = winningAmount;
	}

	public static WinningType getWinningType(int sameNumberCount, boolean isBonusMatching) {
		return Arrays.stream(WinningType.values())
				.filter(t -> (t.sameNumberCount == sameNumberCount)
						&& (t.bonusType.isMatching(isBonusMatching)))
				.findFirst()
				.orElse(NONE);
	}

	public boolean isBonusTRUE() {
		return bonusType == BonusType.TRUE;
	}

	public boolean isWinning() {
		return this != NONE;
	}

	public BigInteger calculateEarning(int num) {
		return winningAmount.multiply(new BigInteger(Integer.toString(num)));
	}

	public int getSameNumberCount() {
		return sameNumberCount;
	}

	public BigInteger getWinningAmount() {
		return winningAmount;
	}
}
