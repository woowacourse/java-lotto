package lotto.domain;

import java.util.List;
import java.util.stream.Stream;

public class Ranks {
	private final List<Rank> ranks;

	public Ranks(final List<Rank> ranks) {
		this.ranks = ranks;
	}

	public int getCountOf(final Rank input) {
		return (int)ranks.stream()
			.filter(rank -> rank.equals(input))
			.count();
	}

	public Stream<Rank> stream() {
		return ranks.stream();
	}
}
