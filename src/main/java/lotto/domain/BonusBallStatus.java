package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum BonusBallStatus {
	INCLUDING_OR_NOT(Arrays.asList(true, false)),
	INCLUDING(Collections.singletonList(true)),
	NOT_INCLUDING(Collections.singletonList(false));

	private final List<Boolean> isBonusBalls;

	BonusBallStatus(List<Boolean> isBonusBalls) {
		this.isBonusBalls = isBonusBalls;
	}

	public boolean contains(boolean isBonusBall) {
		return isBonusBalls.contains(isBonusBall);
	}
}
