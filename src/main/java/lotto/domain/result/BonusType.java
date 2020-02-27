package lotto.domain.result;

import java.util.function.Function;

public enum BonusType {
	NO_MATTER(isBonusMatching -> true),
	TRUE(isBonusMatching -> isBonusMatching),
	FALSE(isBonusMatching -> !isBonusMatching);

	private Function<Boolean, Boolean> match;

	BonusType(Function<Boolean, Boolean> match) {
		this.match = match;
	}

	public boolean isEqualTo(boolean isBonusMatching) {
		return match.apply(isBonusMatching);
	}
}
