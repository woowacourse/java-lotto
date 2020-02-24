package lotto.domain;

import java.util.function.Function;

public enum BonusType {
	NO_MATTER(bonus -> true),
	TRUE(bonus -> bonus),
	FALSE(bonus -> !bonus);

	private Function<Boolean, Boolean> isBonusMatching;

	BonusType(Function<Boolean, Boolean> isBonusMatching) {
		this.isBonusMatching = isBonusMatching;
	}

	public boolean isMatching(boolean bonus) {
		return isBonusMatching.apply(bonus);
	}
}
