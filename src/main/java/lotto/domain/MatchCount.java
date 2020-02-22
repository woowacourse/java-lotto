package lotto.domain;

public enum MatchCount {
	ZERO(0),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6);

	private final int matchCount;

	MatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	public boolean isSameMatch(int count) {
		return matchCount == count;
	}

	@Override
	public String toString() {
		return String.valueOf(matchCount);
	}
}
