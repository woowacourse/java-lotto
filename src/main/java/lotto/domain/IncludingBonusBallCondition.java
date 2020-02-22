package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum IncludingBonusBallCondition {
	NO_MATTER(Arrays.asList(true, false)),
	INCLUDE(Collections.singletonList(true)),
	NONE_INCLUDE(Collections.singletonList(false));

	private final List<Boolean> acceptableBonusConditions;

	IncludingBonusBallCondition(List<Boolean> acceptableBonusConditions) {
		this.acceptableBonusConditions = acceptableBonusConditions;
	}

	public boolean isAcceptableBonusCondition(boolean isBonusBall) {
		return acceptableBonusConditions.contains(isBonusBall);
	}
}
