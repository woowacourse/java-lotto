package lotto.domain.BonusRequirement;

import java.util.function.Function;

public enum BonusRequirement {
	NO_MATTER(BonusRequirementExpressions::isSatisfiedWhenNoMatter),
	TRUE(BonusRequirementExpressions::isSatisfiedWhenTrue),
	FALSE(BonusRequirementExpressions::isSatisfiedWhenFalse);

	private Function<Boolean, Boolean> satisfyExpression;

	BonusRequirement(Function<Boolean, Boolean> satisfyExpression) {
		this.satisfyExpression = satisfyExpression;
	}

	public boolean isSatisfiedBy(boolean bonus) {
		return satisfyExpression.apply(bonus);
	}
}
