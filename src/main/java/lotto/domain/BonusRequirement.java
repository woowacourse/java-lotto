package lotto.domain;

import java.util.function.Function;

public enum BonusRequirement {
	NO_MATTER(bonus -> true),
	TRUE(bonus -> bonus),
	FALSE(bonus -> !bonus);

	private Function<Boolean, Boolean> bonusRequirement;

	BonusRequirement(Function<Boolean, Boolean> bonusRequirement) {
		this.bonusRequirement = bonusRequirement;
	}

	public boolean isSatisfiedBy(boolean bonus) {
		return bonusRequirement.apply(bonus);
	}
}
