package lotto.domain;

public enum BonusRequirement {
	NO_MATTER,
	TRUE,
	FALSE;

	public boolean isSatisfiedBy(boolean bonus) {
		if (this == NO_MATTER) {
			return true;
		}
		if (this == TRUE && bonus) {
			return true;
		}
		if (this == FALSE && !bonus) {
			return true;
		}
		return false;
	}
}
