package lotto.domain.result;

import java.util.Objects;

import lotto.domain.lottoTicket.LottoTicket;

public class MatchCount {
	private final int matchCount;

	public MatchCount(int matchCount) {
		validate(matchCount);
		this.matchCount = matchCount;
	}

	private void validate(int matchCount) {
		if (0 > matchCount || LottoTicket.TOTAL_SIZE < matchCount) {
			throw new InvalidMatchCountException(InvalidMatchCountException.OUT_OF_BOUND);
		}
	}

	public int getMatchCount() {
		return matchCount;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		MatchCount that = (MatchCount)object;
		return matchCount == that.matchCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matchCount);
	}
}
