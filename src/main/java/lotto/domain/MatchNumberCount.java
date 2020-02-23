package lotto.domain;

public enum MatchNumberCount {
	ZERO(0),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6);

	private final int matchCount;

	MatchNumberCount(int matchCount) {
		this.matchCount = matchCount;
	}

	boolean isSameMatch(int count) {
		return matchCount == count;
	}

	@Override
	public String toString() {
		return String.valueOf(matchCount);
	}
}
