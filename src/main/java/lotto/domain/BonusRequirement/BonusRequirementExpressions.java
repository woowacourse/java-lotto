package lotto.domain.BonusRequirement;

public class BonusRequirementExpressions {
	public static boolean isSatisfiedWhenNoMatter(boolean bonus) {
		return true;
	}

	public static boolean isSatisfiedWhenTrue(boolean bonus) {
		return bonus;
	}

	public static boolean isSatisfiedWhenFalse(boolean bonus) {
		return !bonus;
	}
}
