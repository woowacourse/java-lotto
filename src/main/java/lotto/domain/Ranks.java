package lotto.domain;

import java.util.List;
import java.util.Objects;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ranks ranks1 = (Ranks)o;
		return Objects.equals(ranks, ranks1.ranks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ranks);
	}
}
